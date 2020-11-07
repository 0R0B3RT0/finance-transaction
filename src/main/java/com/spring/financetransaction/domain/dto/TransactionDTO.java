package com.spring.financetransaction.domain.dto;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import lombok.Builder;
import lombok.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Value
@Builder
@JsonInclude(NON_NULL)
public class TransactionDTO {

	@JsonProperty(value = "transaction_id")
	private Long transactionId;
}
