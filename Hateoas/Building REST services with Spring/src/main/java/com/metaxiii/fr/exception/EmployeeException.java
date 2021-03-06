package com.metaxiii.fr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;

@Getter
@AllArgsConstructor
public class EmployeeException extends RuntimeException {
    private final transient ErrorCodeDetail details;

    public EmployeeException(final ErrorCodeDetail details, Object... params) {
        super(MessageFormat.format(details.getMessage(), params));
        this.details = details;
    }
}
