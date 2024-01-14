package com.metaxiii.fr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageList {
  ORDER_NOT_FOUND("Order {0} not found", 404);

  private final String message;
  private final int codeStatus;
}
