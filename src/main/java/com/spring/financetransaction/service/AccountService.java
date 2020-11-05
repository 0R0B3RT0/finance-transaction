package com.spring.financetransaction.service;

import static com.spring.financetransaction.domain.mapper.AccountMapper.toDTO;

import com.spring.financetransaction.controller.dto.AccountCreateDTO;
import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountService {

  @Autowired private AccountRepository accountRepository;
  @Autowired private ValidationService<AccountCreateDTO> validationService;

  public AccountDTO createAccount(AccountCreateDTO accountCreateDTO) {
    validationService.validateAndThrow(accountCreateDTO);

    Account account =
        Account.builder().documentNumber(accountCreateDTO.getDocumentNumber()).build();

    Account persistedAccount = accountRepository.save(account);

    return toDTO(persistedAccount);
  }
}
