package com.metaxiii.fr.config;

import com.metaxiii.fr.exception.DatabindingException;
import com.metaxiii.fr.exception.EmployeeException;
import com.metaxiii.fr.exception.ErrorCodeDetail;
import com.metaxiii.fr.exception.OrderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ApiExceptionHandler {

  @ExceptionHandler(OrderException.class)
  public ResponseEntity<String> handleServiceException(final OrderException exception) {
    final ErrorCodeDetail details = exception.getDetails();
    return ResponseEntity.status(HttpStatus.valueOf(details.getCodeDetails())).body(exception.getMessage());
  }

  @ExceptionHandler(EmployeeException.class)
  public ResponseEntity<String> handleServiceException(final EmployeeException exception) {
    final ErrorCodeDetail details = exception.getDetails();
    return ResponseEntity.status(HttpStatus.valueOf(details.getCodeDetails())).body(exception.getMessage());
  }

  @ExceptionHandler(DatabindingException.class)
  public ResponseEntity<String> handleServiceException(final DatabindingException exception) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
  }
}
