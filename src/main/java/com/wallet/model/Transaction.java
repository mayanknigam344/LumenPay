package com.wallet.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String transactionId;
    private String recipientId;
    private String senderId;
    private long transactionAmount;
    private LocalDateTime transactionDateTime;
    private TransactionType transactionType;
}
