package com.spring.financetransaction;

import com.spring.financetransaction.infrastructure.FinanceTransactionApplication;
import io.restassured.RestAssured;
import org.assertj.core.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

@RunWith(SpringRunner.class)
@ActiveProfiles("integration")
@SpringBootTest(
    classes = {FinanceTransactionApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 0)
public abstract class SpringControllerTest {

  @LocalServerPort private int port;
  @Autowired private JdbcTemplate jdbcTemplate;

  @Before
  public void resetState() {
    RestAssured.port = port;
  }

  @After
  public void cleanDatabase() {
    JdbcTestUtils.deleteFromTables(jdbcTemplate, Arrays.array("transaction", "account"));
  }
}
