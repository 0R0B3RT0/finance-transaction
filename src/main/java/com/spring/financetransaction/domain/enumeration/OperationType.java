package com.spring.financetransaction.domain.enumeration;

import com.spring.financetransaction.domain.exception.ValidationException;
import lombok.Getter;

@Getter
public enum OperationType {
  COMPRA_A_VISTA(1),
  COMPRA_PARCELADA(2),
  SAQUE(3),
  PAGAMENTO(4);

  private int code;

  OperationType(int code) {
    this.code = code;
  }

  public static OperationType getByCode(Integer operationTypeId) {
    for (OperationType value : values()) if (value.code == operationTypeId) return value;

    throw new ValidationException("operationTypeId", "must be valid");
  }
}
