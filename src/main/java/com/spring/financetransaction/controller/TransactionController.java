package com.spring.financetransaction.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.financetransaction.controller.dto.TransactionCreateDTO;
import com.spring.financetransaction.domain.dto.TransactionDTO;
import com.spring.financetransaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@DefaultProperties(
    groupKey = "transaction",
    commandProperties =
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "800"))
@RestController
@RequestMapping(value = "transactions", produces = APPLICATION_JSON_VALUE)
public class TransactionController {

  @Autowired private TransactionService transactionService;

  @PostMapping
  public ResponseEntity<TransactionDTO> createTransaction(
      @RequestBody TransactionCreateDTO transactionCreateDTO) {
    TransactionDTO transactionDTO = transactionService.createTransaction(transactionCreateDTO);
    return ResponseEntity.ok(transactionDTO);
  }
}
