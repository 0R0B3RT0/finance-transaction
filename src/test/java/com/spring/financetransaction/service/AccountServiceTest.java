package com.spring.financetransaction.service;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.spring.financetransaction.BaseUnitTest;
import com.spring.financetransaction.controller.dto.AccountCreateDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.exception.ValidationException;
import com.spring.financetransaction.domain.repository.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AccountServiceTest extends BaseUnitTest {

  private static final String ACCOUNT_DOCUMENT = "12345678910";
  private static final long ACCOUNT_ID = 123;
  private AccountCreateDTO accountCreateDTO;

  @InjectMocks private AccountService accountService;
  @Mock private AccountRepository accountRepository;
  @Mock private ValidationService<AccountCreateDTO> validationService;

  @Before
  public void setup() {
    accountCreateDTO = AccountCreateDTO.builder().documentNumber(ACCOUNT_DOCUMENT).build();
    Account account =
        Account.builder()
            .documentNumber(accountCreateDTO.getDocumentNumber())
            .id(ACCOUNT_ID)
            .build();
    doReturn(account).when(accountRepository).save(account);
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
}
