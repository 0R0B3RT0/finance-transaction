package com.spring.financetransaction.service.query;

import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.domain.mapper.AccountMapper;
import com.spring.financetransaction.domain.repository.AccountRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(value = Transactional.TxType.NOT_SUPPORTED)
public class AccountQueryService {
  @Autowired private AccountRepository accountRepository;

  public Optional<AccountDTO> findAccountById(long account_id) {
    return accountRepository.findById(account_id).map(AccountMapper::toDTO);
  }
}
