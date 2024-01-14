package com.metaxiii.fr.exception;

import java.text.MessageFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderException extends RuntimeException {

  private final transient ErrorCodeDetail details;

  public OrderException(final ErrorCodeDetail details, Object... params) {
    super(MessageFormat.format(details.getMessage(), params));
    this.details = details;
  }
}
