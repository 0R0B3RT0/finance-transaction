package com.spring.financetransaction.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.http.HttpStatus.OK;

import com.spring.financetransaction.BaseUnitTest;
import com.spring.financetransaction.controller.dto.TransactionCreateDTO;
import com.spring.financetransaction.domain.dto.TransactionDTO;
import com.spring.financetransaction.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

public class TransactionControllerTest extends BaseUnitTest {

  private TransactionCreateDTO transactionCreateDTO;
  private TransactionDTO transactionDTO;
  @InjectMocks private TransactionController transactionController;
  @Mock private TransactionService transactionService;

  @Before
  public void setup() {
    transactionCreateDTO = TransactionCreateDTO.builder().build();
    transactionDTO = TransactionDTO.builder().build();
    doReturn(transactionDTO).when(transactionService).createTransaction(transactionCreateDTO);
  }

  @Test
  public void mustBaCreateTransaction() {
    ResponseEntity<TransactionDTO> account =
        transactionController.createTransaction(transactionCreateDTO);

    assertThat(account.getStatusCode(), equalTo(OK));
    verify(transactionService).createTransaction(transactionCreateDTO);
  }
}
