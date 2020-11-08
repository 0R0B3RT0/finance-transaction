package com.spring.financetransaction.domain.enumeration;

import com.spring.financetransaction.domain.exception.ValidationException;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public enum OperationType {
  CASH_PURCHASE(1) {
    @Override
    public BigDecimal calculate(BigDecimal amount) {
      return amount.abs().negate();
    }
  },
  INSTALLMENT_PURCHASE(2) {
    @Override
    public BigDecimal calculate(BigDecimal amount) {
      return amount.abs().negate();
    }
  },
  WITHDRAW(3) {
    @Override
    public BigDecimal calculate(BigDecimal amount) {
      return amount.abs().negate();
    }
  },
  PAYMENT(4) {
    @Override
    public BigDecimal calculate(BigDecimal amount) {
      return amount.abs();
    }
  };

  private int code;

  OperationType(int code) {
    this.code = code;
  }

  public static OperationType getByCode(Integer operationTypeId) {
    for (OperationType value : values()) if (value.code == operationTypeId) return value;

    throw new ValidationException("operationTypeId", "must be valid");
  }

  public abstract BigDecimal calculate(BigDecimal amount);
}
