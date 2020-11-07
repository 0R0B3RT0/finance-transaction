package com.spring.financetransaction.domain.mapper;

import com.spring.financetransaction.domain.dto.TransactionDTO;
import com.spring.financetransaction.domain.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public static TransactionDTO toDTO(Transaction entity) {
    return TransactionDTO.builder().transactionId(entity.getId()).build();
  }
}
