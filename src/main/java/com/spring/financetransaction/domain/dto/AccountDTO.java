package com.spring.financetransaction.domain.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@JsonInclude(NON_NULL)
@EqualsAndHashCode
@ToString
public class AccountDTO {

  private Long accountId;

  private String documentNumber;
}
