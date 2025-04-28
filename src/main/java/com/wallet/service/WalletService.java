package com.wallet.service;

import com.wallet.exception.InvalidAmountException;
import com.wallet.model.User;
import com.wallet.model.Wallet;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    public Wallet addAmount(User user, long amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount to be added must be greater than zero.");
        }
        Wallet wallet = user.getWallet();
        long updatedBalance = wallet.getWalletBalance() + amount;
        wallet.setWalletBalance(updatedBalance);
        return wallet;
    }
}
