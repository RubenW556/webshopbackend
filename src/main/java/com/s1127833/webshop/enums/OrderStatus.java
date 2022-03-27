package com.s1127833.webshop.enums;

public enum OrderStatus {
    PROCESSED(0),
    UNDERWAY(1),
    DELIVERED(2);

    private final int value;

    OrderStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
