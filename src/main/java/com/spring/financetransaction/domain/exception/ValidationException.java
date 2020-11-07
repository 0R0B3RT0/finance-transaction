package com.spring.financetransaction.domain.exception;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class ValidationException extends RuntimeException {

  @Getter private final Map<String, String> errors;

  public ValidationException(Map<String, String> errors) {
    super(errors.toString());
    this.errors = errors;
  }

  public ValidationException(String key, String error) {
    super(String.format("{%s=%s}", key, error));
    this.errors = buildMap(key, error);
  }

  private Map<String, String> buildMap(String key, String error) {
    Map<String, String> errors = new HashMap<>();
    errors.put(key, error);
    return errors;
  }
}
