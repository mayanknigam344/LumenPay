package com.wallet.repository;


import com.wallet.model.Transaction;
import com.wallet.model.TransactionType;
import com.wallet.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Repository
public class TransactionRepository {
   HashMap<String,List<Transaction>> transactions = new HashMap<>();

   public void addTransaction(User user, Transaction transaction){
      transactions.putIfAbsent(user.getUserId(),new ArrayList<>());
      transactions.get(user.getUserId()).add(transaction);
   }

   public List<Transaction> getTransactionsSortedByAmount(User user){
      List<Transaction> transactions = getTransactionHistory(user.getUserId());
      return transactions.stream()
              .sorted(Comparator.comparing(Transaction::getTransactionAmount))
              .toList();
   }

   public List<Transaction> getTransactionsSortedByDateTime(User user){
      List<Transaction> transactions = getTransactionHistory(user.getUserId());
      return transactions.stream()
              .sorted(Comparator.comparing(Transaction::getTransactionDateTime))
              .toList();
   }

   public List<Transaction> getTransactionsByType(User user, TransactionType transactionType){
      List<Transaction> transactions = getTransactionHistory(user.getUserId());
      return transactions.stream()
              .filter(transaction -> transaction.getTransactionType().equals(transactionType))
              .toList();
   }

   public List<Transaction> getTransactionHistory(String userId){
      return transactions.get(userId);
   }
}
