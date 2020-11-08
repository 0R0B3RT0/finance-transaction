package com.spring.financetransaction.controller.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionCreateDTO {

  @NotNull(message = "must not be null")
  private Long accountId;

  @NotNull(message = "must not be null")
  private Integer operationTypeId;

  @NotNull(message = "must not be null")
  private BigDecimal amount;
}
