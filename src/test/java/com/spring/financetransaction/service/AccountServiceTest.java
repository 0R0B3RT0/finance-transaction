package com.spring.financetransaction.service;

import static java.util.Optional.of;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.spring.financetransaction.BaseUnitTest;
import com.spring.financetransaction.controller.dto.AccountCreateDTO;
import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.exception.ValidationException;
import com.spring.financetransaction.domain.repository.AccountRepository;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AccountServiceTest extends BaseUnitTest {

  private static final String ACCOUNT_DOCUMENT = "12345678910";
  private static final long ACCOUNT_ID = 123;
  private AccountCreateDTO accountCreateDTO;
  private AccountDTO accountDTO;
  private Account account;

  @InjectMocks private AccountService accountService;
  @Mock private AccountRepository accountRepository;
  @Mock private ValidationService<AccountCreateDTO> validationService;

  @Before
  public void setup() {
    accountCreateDTO = AccountCreateDTO.builder().documentNumber(ACCOUNT_DOCUMENT).build();
    account = Account.builder().id(ACCOUNT_ID).documentNumber(ACCOUNT_DOCUMENT).build();
    doReturn(account).when(accountRepository).save(account);
    accountDTO =
        AccountDTO.builder().accountId(ACCOUNT_ID).documentNumber(ACCOUNT_DOCUMENT).build();
  }

  @Test
  public void mustBeSaveWhenHasValidAccount() {
    Account account =
        Account.builder().documentNumber(accountCreateDTO.getDocumentNumber()).build();

    accountService.createAccount(accountCreateDTO);

    verify(accountRepository).save(account);
  }

  @Test(expected = ValidationException.class)
  public void mustBeExceptionWhenDoesNotHaveDocument() {
    accountCreateDTO = AccountCreateDTO.builder().documentNumber(null).build();
    when(validationService.validateAndThrow(accountCreateDTO)).thenThrow(ValidationException.class);

    accountService.createAccount(accountCreateDTO);

    verifyNoMoreInteractions(accountRepository);
  }

  @Test
  public void mustBeFindWhenExistTheAccount() {
    doReturn(of(account)).when(accountRepository).findById(ACCOUNT_ID);

    Optional<AccountDTO> account = accountService.findAccountById(ACCOUNT_ID);

    assertThat(account.isPresent(), is(true));
    assertThat(account.get(), equalTo(accountDTO));
  }
}
