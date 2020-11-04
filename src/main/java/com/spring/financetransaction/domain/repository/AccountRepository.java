package com.spring.financetransaction.domain.repository;

import com.spring.financetransaction.domain.entity.Account;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {}
