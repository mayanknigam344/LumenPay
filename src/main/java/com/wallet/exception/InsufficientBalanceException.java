package com.wallet.exception;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String errorMessage){
        super(errorMessage);
    }
}
