package com.spring.financetransaction.domain.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
  @JsonProperty("account_id")
  private Long accountId;

  @JsonProperty("document_number")
  private String documentNumber;
}
