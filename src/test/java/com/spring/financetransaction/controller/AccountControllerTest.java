package com.spring.financetransaction.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;

import com.spring.financetransaction.BaseUnitTest;
import com.spring.financetransaction.controller.dto.AccountCreateDTO;
import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class AccountControllerTest extends BaseUnitTest {

  private AccountDTO accountDTO;
  private AccountCreateDTO accountCreateDTO;
  @InjectMocks private AccountController accountController;
  @Mock private AccountService accountService;

  @Before
  public void setup() {
    doReturn(accountDTO).when(accountService).createAccount(accountCreateDTO);
  }

  @Test
  public void mustBaCreateNewAccountWhenHasValidDocument() {
    ResponseEntity<AccountDTO> account = accountController.createNewAccount(accountCreateDTO);

    assertThat(account.getStatusCode(), equalTo(OK));
    verify(accountService).createAccount(accountCreateDTO);
  }
}
