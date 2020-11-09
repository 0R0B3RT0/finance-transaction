package com.spring.financetransaction.service;

import static com.spring.financetransaction.domain.mapper.TransactionMapper.toDTO;

import com.spring.financetransaction.controller.dto.TransactionCreateDTO;
import com.spring.financetransaction.domain.dto.TransactionDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.entity.Transaction;
import com.spring.financetransaction.domain.enumeration.OperationType;
import com.spring.financetransaction.domain.repository.TransactionRepository;
import com.spring.financetransaction.service.query.AccountQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {

  @Autowired private ValidationService<TransactionCreateDTO> transactionCreateDTOValidation;

  @Autowired private TransactionRepository transactionRepository;

  @Autowired private AccountQueryService accountQueryService;

  public TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO) {
    transactionCreateDTOValidation.validateAndThrow(transactionCreateDTO);
    OperationType operationType =
        OperationType.getByCode(transactionCreateDTO.getOperationTypeId());

    Account account = accountQueryService.findAccountById(transactionCreateDTO.getAccountId());

    Transaction transaction =
        Transaction.builder()
            .account(account)
            .operationType(operationType)
            .amount(operationType.calculate(transactionCreateDTO.getAmount()))
            .build();
    Transaction transactionPersisted = transactionRepository.save(transaction);

    return toDTO(transactionPersisted);
  }
}
