package com.wallet.service;

import com.wallet.exception.InsufficientBalanceException;
import com.wallet.model.Transaction;
import com.wallet.model.TransactionType;
import com.wallet.model.User;
import com.wallet.model.Wallet;
import com.wallet.repository.TransactionRepository;
import com.wallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public void initiateTransaction(Transaction transaction) {
        String senderId = transaction.getSenderId();
        String recipientId = transaction.getRecipientId();

        log.info("Initiating transaction: sender={}, recipient={}, amount={}",
                senderId, recipientId, transaction.getTransactionAmount());

        User sender = userRepository.getUser(senderId);
        User receiver = userRepository.getUser(recipientId);

        long transactionAmount = transaction.getTransactionAmount();

        validateSufficientBalance(sender, transactionAmount);

        String transactionId = generateTransactionId();

        processSendTransaction(sender, transactionAmount, transaction, transactionId);
        processReceiveTransaction(receiver, transactionAmount, transaction, transactionId);

        log.info("Transaction completed successfully. transactionId={}", transactionId);
    }

    private void processSendTransaction(User sender, long transactionAmount, Transaction originalTransaction, String transactionId) {
        long updatedBalance = sender.getWallet().getWalletBalance() - transactionAmount;
        Wallet updatedWallet = sender.getWallet().toBuilder()
                .walletBalance(updatedBalance)
                .build();
        sender.setWallet(updatedWallet);

        Transaction sendTransaction = originalTransaction.toBuilder()
                .transactionType(TransactionType.SEND)
                .transactionId(transactionId)
                .build();

        transactionRepository.addTransaction(sender, sendTransaction);

        log.info("Processed SEND transaction for senderId={}, new balance={}",
                sender.getUserId(), updatedBalance);
    }

    private void processReceiveTransaction(User receiver, long transactionAmount, Transaction originalTransaction, String transactionId) {
        long updatedBalance = receiver.getWallet().getWalletBalance() + transactionAmount;
        Wallet updatedWallet = receiver.getWallet().toBuilder()
                .walletBalance(updatedBalance)
                .build();
        receiver.setWallet(updatedWallet);

        Transaction receiveTransaction = originalTransaction.toBuilder()
                .transactionType(TransactionType.RECEIVE)
                .transactionId(transactionId)
                .build();

        transactionRepository.addTransaction(receiver, receiveTransaction);

        log.info("Processed RECEIVE transaction for receiverId={}, new balance={}",
                receiver.getUserId(), updatedBalance);
    }

    private void validateSufficientBalance(User sender, long transactionAmount) {
        if (sender.getWallet().getWalletBalance() < transactionAmount) {
            log.warn("Insufficient balance for senderId={}, required={}, available={}",
                    sender.getUserId(), transactionAmount, sender.getWallet().getWalletBalance());
            throw new InsufficientBalanceException("Insufficient balance to complete the transaction.");
        }
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
