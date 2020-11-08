package com.spring.financetransaction.service.query;

import static java.util.Optional.of;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

import com.spring.financetransaction.BaseUnitTest;
import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AccountQueryServiceTest extends BaseUnitTest {

  private Account account;
  private AccountDTO accountDTO;

  @InjectMocks private AccountQueryService accountQueryService;
  @Mock private AccountRepository accountRepository;

  @Before
  public void setup() {
    account = Account.builder().id(ACCOUNT_ID).documentNumber(ACCOUNT_DOCUMENT).build();
    accountDTO =
        AccountDTO.builder().accountId(ACCOUNT_ID).documentNumber(ACCOUNT_DOCUMENT).build();
  }

  @Test
  public void mustBeFindWhenExistTheAccount() {
    doReturn(of(account)).when(accountRepository).findById(ACCOUNT_ID);

    AccountDTO account = accountQueryService.findAccountById(ACCOUNT_ID);

    assertThat(account, equalTo(accountDTO));
  }
}
