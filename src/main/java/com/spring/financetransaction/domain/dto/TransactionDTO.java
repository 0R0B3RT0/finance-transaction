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
@EqualsAndHashCode
@ToString
@JsonInclude(NON_NULL)
public class TransactionDTO {

  @JsonProperty(value = "transaction_id")
  private Long transactionId;
}
