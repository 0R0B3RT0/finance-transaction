package com.spring.financetransaction.infrastructure;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Optional.ofNullable;

import java.io.Serializable;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@EqualsAndHashCode
public class ErrorDTO implements Serializable {

  private static final long serialVersionUID = -3899837726682487993L;

  private String code;
  private String details;

  public String getMessage() {
    if (isNullOrEmpty(this.code)) return this.details;
    if (isNullOrEmpty(this.details)) return this.code;

    return this.code + ": " + this.details;
  }

  public ErrorDTO(String code, String details) {
    this.code = ofNullable(code).orElse("");
    this.details = ofNullable(details).orElse("");
  }
}
