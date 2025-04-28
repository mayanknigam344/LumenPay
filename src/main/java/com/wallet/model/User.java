package com.wallet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userId;
    private Wallet wallet;

    public User(String userId){
        this.userId = userId;
        this.wallet = new Wallet(0);
    }
}
