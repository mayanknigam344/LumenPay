package com.wallet.model.payment;

import lombok.Getter;

@Getter
public abstract class PaymentMethodDetails {
     long amountToBeAdded;
     PaymentType paymentType;
}
