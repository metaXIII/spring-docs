package com.metaxiii.fr.exception;

import org.springframework.validation.BindingResult;

public class DatabindingException extends RuntimeException {
    public DatabindingException(BindingResult details) {
        super(details.toString());
    }
}
