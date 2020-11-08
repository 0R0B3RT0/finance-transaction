package com.spring.financetransaction.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import com.spring.financetransaction.BaseUnitTest;
import com.spring.financetransaction.controller.dto.AccountCreateDTO;
import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.service.AccountService;
import com.spring.financetransaction.service.query.AccountQueryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class AccountControllerTest extends BaseUnitTest {

  private static final String ACCOUNT_DOCUMENT = "12345678910";
  private AccountDTO accountDTO;
  private AccountCreateDTO accountCreateDTO;
  private long ACCOUNT_ID = 321;
  @InjectMocks private AccountController accountController;
  @Mock private AccountService accountService;
  @Mock private AccountQueryService accountQueryService;

  @Before
  public void setup() {
    accountDTO = AccountDTO.builder().documentNumber(ACCOUNT_DOCUMENT).build();
    accountCreateDTO = AccountCreateDTO.builder().documentNumber(ACCOUNT_DOCUMENT).build();
    doReturn(accountDTO).when(accountService).createAccount(accountCreateDTO);
  }

  @Test
  public void mustBaCreateNewAccountWhenHasValidDocument() {
    ResponseEntity<AccountDTO> account = accountController.createNewAccount(accountCreateDTO);

    assertThat(account.getStatusCode(), equalTo(CREATED));
    verify(accountService).createAccount(accountCreateDTO);
  }

  @Test
  public void mustBeFindWhenExistTheAccount() {
    doReturn(accountDTO).when(accountQueryService).findAccountById(ACCOUNT_ID);

    ResponseEntity<AccountDTO> accountResponse = accountController.getAccountByID(ACCOUNT_ID);

    assertThat(accountResponse.getStatusCode(), equalTo(OK));
    assertThat(accountResponse.getBody(), equalTo(accountDTO));
  }
}
