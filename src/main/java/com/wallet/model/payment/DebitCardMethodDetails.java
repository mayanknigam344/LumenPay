package com.wallet.model.payment;

public class DebitCardMethodDetails extends PaymentMethodDetails{
     String debitCardNumber;
     String cvvNumber;

    public DebitCardMethodDetails(String debitCardNumber,String cvvNumber, long amountToAdded){
        this.debitCardNumber = debitCardNumber;
        this.cvvNumber = cvvNumber;
        this.amountToBeAdded = amountToAdded;
        this.paymentType = PaymentType.DEBIT_CARD;
    }
}
