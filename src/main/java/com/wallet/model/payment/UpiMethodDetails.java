package com.wallet.model.payment;

public class UpiMethodDetails extends PaymentMethodDetails{
    private String upiId;

    public UpiMethodDetails(String upiId, long amountToBeAdded){
        this.upiId = upiId;
        this.amountToBeAdded = amountToBeAdded;
        this.paymentType = PaymentType.UPI;
    }
}
