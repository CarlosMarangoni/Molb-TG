package com.carlos.costura.domain.model.enumeration;

public enum PaymentMethod {
    PAYPAL("Paypal");

    private String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
