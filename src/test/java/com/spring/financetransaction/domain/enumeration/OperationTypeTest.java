package com.spring.financetransaction.domain.enumeration;

import static com.spring.financetransaction.domain.enumeration.OperationType.CASH_PURCHASE;
import static com.spring.financetransaction.domain.enumeration.OperationType.INSTALLMENT_PURCHASE;
import static com.spring.financetransaction.domain.enumeration.OperationType.PAYMENT;
import static com.spring.financetransaction.domain.enumeration.OperationType.WITHDRAW;
import static java.math.BigDecimal.ONE;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import org.junit.Test;

public class OperationTypeTest {

  @Test
  public void mustBeNegativeValueWhenHasCashPurchase() {
    BigDecimal value = CASH_PURCHASE.calculate(ONE);

    assertThat(value, equalTo(ONE.negate()));
  }

  @Test
  public void mustBeNegativeValueWhenHasInstallmentPurchase() {
    BigDecimal value = INSTALLMENT_PURCHASE.calculate(ONE);

    assertThat(value, equalTo(ONE.negate()));
  }

  @Test
  public void mustBeNegativeValueWhenHasWithdraw() {
    BigDecimal value = WITHDRAW.calculate(ONE);

    assertThat(value, equalTo(ONE.negate()));
  }

  @Test
  public void mustBePositiveValueWhenHasPayment() {
    BigDecimal value = PAYMENT.calculate(ONE);

    assertThat(value, equalTo(ONE));
  }
}
