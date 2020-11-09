package com.spring.financetransaction.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.spring.financetransaction.controller.dto.AccountCreateDTO;
import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.service.AccountService;
import com.spring.financetransaction.service.query.AccountQueryService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/accounts", produces = APPLICATION_JSON_VALUE)
public class AccountController {

  @Autowired private AccountService accountService;
  @Autowired private AccountQueryService accountQueryService;

  @PostMapping
  public ResponseEntity<AccountDTO> createNewAccount(
      @RequestBody AccountCreateDTO accountCreateDTO) {
    AccountDTO account = accountService.createAccount(accountCreateDTO);
    return ResponseEntity.created(URI.create("/accounts/" + account.getAccountId())).build();
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<AccountDTO> getAccountByID(
      @PathVariable(name = "accountId") Long accountId) {
    return ResponseEntity.ok(accountQueryService.findAccountDTOById(accountId));
  }
}
