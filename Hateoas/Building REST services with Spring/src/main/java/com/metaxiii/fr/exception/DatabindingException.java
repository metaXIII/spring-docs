package com.metaxiii.fr.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public class DatabindingException extends RuntimeException {
    private final String message;

    public DatabindingException(BindingResult details) {
        super(details.toString());
        this.message = Optional.ofNullable(details.getFieldError())
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
