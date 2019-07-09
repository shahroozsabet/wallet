package com.leovegas.wallet.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    DISABLE, ENABLE;

    @JsonValue
    public int toValue() {
        return ordinal();
    }

}
