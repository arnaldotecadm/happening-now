package com.happeningnow.enuns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

public enum statusEnum {

    PENDING("Pending"),
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    DELETED("Deleted"),
    PENDING_ANALYSIS("Pending analysis");

    private String status;
}