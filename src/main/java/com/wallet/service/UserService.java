package com.wallet.service;

import com.wallet.model.User;
import com.wallet.model.Wallet;
import com.wallet.model.payment.PaymentMethodDetails;
import com.wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final WalletService walletService;
    private final UserRepository userRepository;

    public void addUser(User user){
        userRepository.addUser(user);
    }

    public User getUser(String userId){
        return userRepository.getUser(userId);
    }

    public void addAmountToWallet(User user, PaymentMethodDetails paymentMethodDetails){
        long amountToBeAdded = paymentMethodDetails.getAmountToBeAdded();
        Wallet updatedWallet = walletService.addAmount(user,amountToBeAdded);
        user.setWallet(updatedWallet);
    }
}
