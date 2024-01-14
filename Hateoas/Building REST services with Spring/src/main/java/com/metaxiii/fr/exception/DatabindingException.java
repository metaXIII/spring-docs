package com.metaxiii.fr.exception;

import java.util.Optional;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class DatabindingException extends RuntimeException {

  private final String message;

  public DatabindingException(BindingResult details) {
    super(details.toString());
    this.message =
      Optional.ofNullable(details.getFieldError()).map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("");
  }

  @Override
  public String getMessage() {
    return message;
  }
}
