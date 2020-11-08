package com.spring.financetransaction.controller;

import static com.spring.financetransaction.domain.enumeration.OperationType.CASH_PURCHASE;
import static java.math.BigDecimal.valueOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.spring.financetransaction.SpringControllerTest;
import com.spring.financetransaction.controller.dto.TransactionCreateDTO;
import io.restassured.RestAssured;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

public class TransactionControllerIntegrationTest extends SpringControllerTest {
  private static final String CONTROLLER_PATH = "/transactions";
  private long ACCOUNT_ID = 826;
  private BigDecimal AMOUNT = valueOf(153.00);
  private TransactionCreateDTO transactionCreateDTO;

  @Before
  public void setup() {
    transactionCreateDTO =
        TransactionCreateDTO.builder()
            .accountId(ACCOUNT_ID)
            .amount(AMOUNT)
            .operationTypeId(CASH_PURCHASE.getCode())
            .build();
  }

  @Test
  @Sql("/db/insert_account.sql")
  public void mustBaCreateTransactionWhenHasValidDocument() {
    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(transactionCreateDTO)
        .post(CONTROLLER_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .body("transaction_id", notNullValue());
  }

  @Test
  public void mustBeExceptionWhenThereIsNotAccount() {
    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(transactionCreateDTO)
        .post(CONTROLLER_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND.value())
        .body("message", equalTo("accountId: not founded"));
  }

  @Test
  public void mustBeExceptionWhenAccountIdIsNull() {
    transactionCreateDTO.setAccountId(null);

    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(transactionCreateDTO)
        .post(CONTROLLER_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("message", equalTo("accountId: must not be null"));
  }

  @Test
  public void mustBeExceptionWhenHasInvalidOperationType() {
    transactionCreateDTO.setOperationTypeId(6);

    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(transactionCreateDTO)
        .post(CONTROLLER_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("message", equalTo("operationTypeId: must be valid"));
  }

  @Test
  public void mustBeExceptionWhenOperationTypeIsNull() {
    transactionCreateDTO.setOperationTypeId(null);

    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(transactionCreateDTO)
        .post(CONTROLLER_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("message", equalTo("operationTypeId: must not be null"));
  }

  @Test
  public void mustBeExceptionWhenAmountIsNull() {
    transactionCreateDTO.setAmount(null);

    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(transactionCreateDTO)
        .post(CONTROLLER_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .body("message", equalTo("amount: must not be null"));
  }
}
