package com.spring.financetransaction.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.spring.financetransaction.controller.dto.AccountCreateDTO;
import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.service.AccountService;

@DefaultProperties(
    groupKey = "account",
    commandProperties =
        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "800"))
@RestController
@RequestMapping(value = "/accounts", produces = APPLICATION_JSON_VALUE)
public class AccountController {

  @Autowired private AccountService accountService;

  @PostMapping
  public ResponseEntity<AccountDTO> createNewAccount(
      @RequestBody AccountCreateDTO accountCreateDTO) {
    return ResponseEntity.ok(accountService.createAccount(accountCreateDTO));
  }
}
