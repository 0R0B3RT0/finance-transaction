package com.spring.financetransaction.controller;

import static java.lang.String.format;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.spring.financetransaction.SpringControllerTest;
import com.spring.financetransaction.controller.dto.AccountCreateDTO;
import io.restassured.RestAssured;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

public class AccountControllerIntegrationTest extends SpringControllerTest {

  private static final String CONTROLLER_PATH = "/accounts";
  private static final String ACCOUNT_DOCUMENT = "12345678910";
  private static final int ACCOUNT_ID = 843635;

  @Test
  public void mustBaCreateNewAccountWhenHasValidDocument() {
    AccountCreateDTO accountCreateDTO =
        AccountCreateDTO.builder().documentNumber(ACCOUNT_DOCUMENT).build();
    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(accountCreateDTO)
        .post(CONTROLLER_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .body("documentNumber", equalTo(ACCOUNT_DOCUMENT))
        .body("accountId", notNullValue());
  }

  @Test
  public void mustBaExceptionWhenDoesNotHaveDocument() {
    AccountCreateDTO accountCreateDTO = AccountCreateDTO.builder().documentNumber(null).build();
    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(accountCreateDTO)
        .post(CONTROLLER_PATH)
        .then()
        .assertThat()
        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .body("message", equalTo("{documentNumber=must not be null}"));
  }

  @Test
  public void mustNotFountWhenThereIsNotTheAccount() {
    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .get(format("%s/%d", CONTROLLER_PATH, -1))
        .then()
        .assertThat()
        .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  @Sql("/database/insert_account.sql")
  public void mustBeFindWhenExistTheAccount() {
    RestAssured.given()
        .contentType(APPLICATION_JSON_VALUE)
        .get(format("%s/%d", CONTROLLER_PATH, ACCOUNT_ID))
        .then()
        .assertThat()
        .statusCode(HttpStatus.OK.value())
        .body("accountId", equalTo(ACCOUNT_ID))
        .body("documentNumber", equalTo(ACCOUNT_DOCUMENT));
  }
}
