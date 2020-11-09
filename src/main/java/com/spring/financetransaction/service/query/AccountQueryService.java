package com.spring.financetransaction.service.query;

import static com.spring.financetransaction.domain.mapper.AccountMapper.toDTO;

import com.spring.financetransaction.domain.dto.AccountDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.exception.NotFoundedException;
import com.spring.financetransaction.domain.repository.AccountRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(value = Transactional.TxType.NOT_SUPPORTED)
public class AccountQueryService {
  @Autowired private AccountRepository accountRepository;

  public AccountDTO findAccountDTOById(long accountId) {
    Account account = findAccountById(accountId);
    return toDTO(account);
  }

  public Account findAccountById(long accountId) {
    Optional<Account> account = accountRepository.findByCode(accountId);
    if (account.isEmpty()) throw new NotFoundedException("account", "not founded");

    return account.get();
  }
}
