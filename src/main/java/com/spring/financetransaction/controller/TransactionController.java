package com.spring.financetransaction.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.spring.financetransaction.controller.dto.TransactionCreateDTO;
import com.spring.financetransaction.domain.dto.TransactionDTO;
import com.spring.financetransaction.domain.entity.Transaction;
import com.spring.financetransaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

  @GetMapping("/{accountId}")
  public ResponseEntity<List<Transaction>> getTransaction(
          @PathVariable(name = "accountId") long accountID) {
    List<Transaction> transactions = transactionService.getTransactions(accountID);
    return ResponseEntity.ok(transactions);
  }
}
