package com.spring.financetransaction.domain.mapper;

import static org.hamcrest.Matchers.equalTo;

import com.spring.financetransaction.BaseUnitTest;
import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.domain.entity.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class AccountMapperTest extends BaseUnitTest {

  private AccountDTO expectedAccountDTO;

  @InjectMocks private AccountMapper accountMapper;

  @Before
  public void setup() {
    expectedAccountDTO =
        AccountDTO.builder().accountId(ACCOUNT_ID).documentNumber(ACCOUNT_DOCUMENT).build();
  }

  @Test
  public void entityToDTO() {
    Account account = Account.builder().id(ACCOUNT_ID).documentNumber(ACCOUNT_DOCUMENT).build();

    AccountDTO accountDTO = accountMapper.toDTO(account);

    Assert.assertThat(accountDTO, equalTo(expectedAccountDTO));
  }
}
