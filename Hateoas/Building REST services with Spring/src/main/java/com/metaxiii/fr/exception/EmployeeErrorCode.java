package com.metaxiii.fr.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EmployeeErrorCode implements ErrorCodeDetail {
    EMPLOYEE_NOT_FOUND("Employee {0} not found");

    @Getter
    private final String message;

    @Override
    public String getCodeDetail() {
        return name();
    }
}
