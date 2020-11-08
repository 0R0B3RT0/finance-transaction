package com.spring.financetransaction.controller.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountCreateDTO {

  @NotEmpty(message = "must not be null")
  private String documentNumber;
}
