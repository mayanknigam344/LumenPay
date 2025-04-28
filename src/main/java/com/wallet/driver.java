package com.wallet;

import com.wallet.model.Transaction;
import com.wallet.model.User;
import com.wallet.model.payment.DebitCardMethodDetails;
import com.wallet.model.payment.UpiMethodDetails;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.UserRepository;
import com.wallet.service.TransactionService;
import com.wallet.service.UserService;
import com.wallet.service.WalletService;

public class driver {

    public static void main(String[] args) {
        var userRepository = new UserRepository();
        var transactionRepository = new TransactionRepository();

        var walletService = new WalletService();
        var userService = new UserService(walletService,userRepository);
        var transactionService = new TransactionService(transactionRepository,userService);

        // users
        var user1  = new User("user-id-1");
        var user2 = new User("user-id-2");
        userService.addUser(user1);
        userService.addUser(user2);

        var upiPaymentMethodDetails = new UpiMethodDetails("upi-id",100);
        var debitCardPaymentMethodDetails = new DebitCardMethodDetails("debit-card-id","cvv-number",500);

        // adding some amounts in the users wallet
        userService.addAmountToWallet(user1,upiPaymentMethodDetails);
        userService.addAmountToWallet(user2,debitCardPaymentMethodDetails);

        var transaction = Transaction.builder().transactionAmount(100).senderId(user1.getUserId()).recipientId(user2.getUserId()).build();

        transactionService.initiateTransaction(transaction);
    }
}
