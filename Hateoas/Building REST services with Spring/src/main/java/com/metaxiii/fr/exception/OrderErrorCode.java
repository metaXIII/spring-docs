package com.metaxiii.fr.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCodeDetail {
    ORDER_NOT_FOUND("Order {0} not found");

    @Getter
    private final String message;

    @Override
    public String getCodeDetail() {
        return name();
    }
}
