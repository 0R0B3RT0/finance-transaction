package com.spring.financetransaction.service;

import static com.spring.financetransaction.domain.enumeration.OperationType.PAYMENT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import com.spring.financetransaction.BaseUnitTest;
import com.spring.financetransaction.controller.dto.TransactionCreateDTO;
import com.spring.financetransaction.domain.dto.TransactionDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.entity.Transaction;
import com.spring.financetransaction.domain.exception.ValidationException;
import com.spring.financetransaction.domain.repository.TransactionRepository;
import com.spring.financetransaction.service.query.AccountQueryService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TransactionServiceTest extends BaseUnitTest {

  private TransactionCreateDTO transactionCreateDTO;
  private Transaction transaction;
  private TransactionDTO transactionDTO;
  private Account account;

  @InjectMocks private TransactionService transactionService;
  @Mock private ValidationService<TransactionCreateDTO> transactionCreateDTOValidation;
  @Mock private TransactionRepository transactionRepository;
  @Mock private AccountQueryService accountQueryService;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setup() {
    transactionCreateDTO =
        TransactionCreateDTO.builder()
            .accountId(ACCOUNT_CODE)
            .operationTypeId(PAYMENT.getCode())
            .amount(AMOUNT)
            .build();
    account = Account.builder().code(ACCOUNT_CODE).build();
    transaction =
        Transaction.builder()
            .id(TRANSACTION_ID)
            .amount(AMOUNT)
            .operationType(PAYMENT)
            .account(account)
            .build();
    transactionDTO = TransactionDTO.builder().transactionId(TRANSACTION_ID).build();
    when(transactionCreateDTOValidation.validateAndThrow(any())).thenCallRealMethod();
    doReturn(account).when(accountQueryService).findAccountById(ACCOUNT_CODE);
    doReturn(transaction).when(transactionRepository).save(transaction);
  }

  @Test
  public void mustBeExceptionWhenAccountIdIsNull() {
    expectedException.expect(ValidationException.class);
    expectedException.expectMessage("{accountId=must not be null}");

    transactionCreateDTO.setAccountId(null);

    transactionService.createTransaction(transactionCreateDTO);
  }

  @Test
  public void mustBeExceptionWhenHasInvalidOperationType() {
    expectedException.expect(ValidationException.class);
    expectedException.expectMessage("{operationTypeId=must be valid}");
    transactionCreateDTO.setOperationTypeId(6);

    transactionService.createTransaction(transactionCreateDTO);
  }

  @Test
  public void mustBeExceptionWhenOperationTypeIsNull() {
    expectedException.expect(ValidationException.class);
    expectedException.expectMessage("{operationTypeId=must not be null}");
    transactionCreateDTO.setOperationTypeId(null);

    transactionService.createTransaction(transactionCreateDTO);
  }

  @Test
  public void mustBeExceptionWhenAmountIsNull() {
    expectedException.expect(ValidationException.class);
    transactionCreateDTO.setAmount(null);

    transactionService.createTransaction(transactionCreateDTO);
  }

  @Test
  public void mustBeSuccessWhenHasValidTransaction() {
    TransactionDTO transaction = transactionService.createTransaction(transactionCreateDTO);

    assertThat(transaction, equalTo(transactionDTO));
  }
}
