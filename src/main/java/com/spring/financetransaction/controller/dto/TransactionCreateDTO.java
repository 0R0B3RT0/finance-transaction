package com.spring.financetransaction.controller.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.JsonProperty;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionCreateDTO {

	@NotNull(message = "must not be null")
	@JsonProperty(value = "account_id")
	private Long accountId;

	@NotNull(message = "must not be null")
	@JsonProperty(value = "operation_type_id")
	private Integer operationTypeId;

	@NotNull(message = "must not be null")
	@JsonProperty(value = "amount")
	private BigDecimal amount;

}
