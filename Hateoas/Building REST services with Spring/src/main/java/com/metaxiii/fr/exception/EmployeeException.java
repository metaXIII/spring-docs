package com.metaxiii.fr.exception;

import com.metaxiii.fr.model.Employee;
import lombok.Getter;

import java.text.MessageFormat;

public class EmployeeException extends RuntimeException {
    @Getter
    private ErrorCodeDetail details;

    public EmployeeException() {}

    public EmployeeException(ErrorCodeDetail details) {
        super(details.getMessage());
        this.details = details;
    }

    public EmployeeException(ErrorCodeDetail details, Object... params) {
        super(MessageFormat.format(details.getMessage(), params));
        this.details = details;
    }

    public EmployeeException(String message) {
        super(message);
    }

    public EmployeeException(Throwable cause) {
        super(cause);
    }

    public EmployeeException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeException(String message, Object... params) {
        super(MessageFormat.format(message, params));
    }

    public EmployeeException(String message, Throwable cause, Object... params) {
        super(MessageFormat.format(message, params), cause);
    }
}
