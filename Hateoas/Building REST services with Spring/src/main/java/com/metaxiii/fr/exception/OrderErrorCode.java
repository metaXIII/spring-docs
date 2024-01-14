package com.metaxiii.fr.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCodeDetail {
  ORDER_NOT_FOUND("Order {0} not found", 404);

  private final String message;
  private final int codeStatus;

  @Override
  public int getCodeDetails() {
    return codeStatus;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
