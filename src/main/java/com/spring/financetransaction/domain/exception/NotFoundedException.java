package com.spring.financetransaction.domain.exception;

import lombok.Getter;

public class NotFoundedException extends RuntimeException {

  @Getter private String code;
  @Getter private String details;

  public NotFoundedException(String code, String details) {
    super(String.format("{%s=%s}", code, details));
    this.code = code;
    this.details = details;
  }
}
