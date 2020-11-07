package com.spring.financetransaction;

import java.math.BigDecimal;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseUnitTest {

  protected static long ACCOUNT_ID = 826;
  protected static final String ACCOUNT_DOCUMENT = "12345678910";
  protected static long TRANSACTION_ID = 983213;
  protected static BigDecimal AMOUNT = BigDecimal.valueOf(153.00);
}
