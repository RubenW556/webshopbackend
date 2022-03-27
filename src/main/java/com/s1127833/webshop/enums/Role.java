package com.s1127833.webshop.enums;

public enum Role {
    ROLE_CUSTOMER(0),
    ROLE_OWNER(1);

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


}
