package com.s1127833.webshop.model;

public enum Role {
    CUSTOMER(0),
    OWNER(1);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
