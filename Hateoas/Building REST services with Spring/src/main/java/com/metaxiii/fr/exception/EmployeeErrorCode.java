package com.metaxiii.fr.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EmployeeErrorCode implements ErrorCodeDetail {
    EMPLOYEE_NOT_FOUND("Employee {0} not found", 404);

    private final String message;
    private final int status;

    @Override
    public int getCodeDetails() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
