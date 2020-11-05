package com.spring.financetransaction.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCircuitBreaker
@EnableHystrixDashboard
@EntityScan(basePackages = "com.spring.financetransaction.domain.entity")
@EnableJpaRepositories(basePackages = "com.spring.financetransaction.domain.repository")
@SpringBootApplication(scanBasePackages = "com.spring.financetransaction")
public class FinanceTransactionApplication {

  public static void main(String[] args) {
    SpringApplication.run(FinanceTransactionApplication.class, args);
  }
}
