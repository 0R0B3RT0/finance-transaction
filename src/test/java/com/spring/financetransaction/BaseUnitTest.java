package com.spring.financetransaction;

import java.math.BigDecimal;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseUnitTest {

  protected long ACCOUNT_ID = 826;
  protected long TRANSACTION_ID = 983213;
  protected BigDecimal AMOUNT = BigDecimal.valueOf(153.00);
}
