package com.spring.financetransaction.domain.mapper;

import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.domain.entity.Account;

public class AccountMapper {

  public static AccountDTO toDTO(Account entity) {
    return AccountDTO.builder()
        .accountId(entity.getCode())
        .documentNumber(entity.getDocumentNumber())
        .build();
  }
}
