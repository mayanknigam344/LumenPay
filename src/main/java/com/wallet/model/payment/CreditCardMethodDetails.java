package com.wallet.model.payment;

public class CreditCardMethodDetails extends PaymentMethodDetails{
    String creditCardNumber;
    String cvvNumber;

    public CreditCardMethodDetails(String creditCardNumber,String cvvNumber, long amountToAdded){
        this.creditCardNumber = creditCardNumber;
        this.cvvNumber = cvvNumber;
        this.amountToBeAdded = amountToAdded;
        this.paymentType = PaymentType.CREDIT_CARD;
    }
}
