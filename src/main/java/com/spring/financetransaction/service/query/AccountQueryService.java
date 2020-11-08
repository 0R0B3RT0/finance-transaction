package com.spring.financetransaction.service.query;

import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.exception.NotFoundedException;
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

  public AccountDTO findAccountById(long account_id) {
    Optional<Account> account = accountRepository.findById(account_id);
    if (account.isEmpty()) throw new NotFoundedException("account", "not founded");

    return AccountMapper.toDTO(account.get());
  }
}
