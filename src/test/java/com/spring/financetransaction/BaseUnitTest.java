package com.spring.financetransaction;

import static java.util.UUID.fromString;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseUnitTest {

  protected static long ACCOUNT_CODE = 826;
  protected static UUID ACCOUNT_UUID = fromString("BD8CF80D-B3C2-4D41-B2B3-A9C1AB81FC7E");
  protected static final String ACCOUNT_DOCUMENT = "12345678910";
  protected static UUID TRANSACTION_ID = fromString("728B54A5-D5DC-4544-8262-D6C319E3CC61");
  protected static BigDecimal AMOUNT = BigDecimal.valueOf(153.00);
}
