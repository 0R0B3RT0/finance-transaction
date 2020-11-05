package com.spring.financetransaction.domain.repository;

import com.spring.financetransaction.domain.entity.Transaction;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

  List<Transaction> findAllByEnabledTrue();
}
