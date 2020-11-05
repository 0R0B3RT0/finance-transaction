package com.spring.financetransaction;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.financetransaction.infrastructure.FinanceTransactionApplication;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@ActiveProfiles("integration")
@SpringBootTest(
    classes = {FinanceTransactionApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public abstract class SpringControllerTest {

  @LocalServerPort private int port;

  @Before
  public void resetState() {
    RestAssured.port = port;
  }
}
