package com.spring.financetransaction.service;

import static com.spring.financetransaction.domain.mapper.TransactionMapper.toDTO;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.financetransaction.controller.dto.TransactionCreateDTO;
import com.spring.financetransaction.domain.dto.TransactionDTO;
import com.spring.financetransaction.domain.entity.Account;
import com.spring.financetransaction.domain.entity.Transaction;
import com.spring.financetransaction.domain.enumeration.OperationType;
import com.spring.financetransaction.domain.exception.ValidationException;
import com.spring.financetransaction.domain.repository.AccountRepository;
import com.spring.financetransaction.domain.repository.TransactionRepository;

@Slf4j
@Service
public class TransactionService {

	@Autowired
	private ValidationService<TransactionCreateDTO> transactionCreateDTOValidation;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;

	public TransactionDTO createTransaction(TransactionCreateDTO transactionCreateDTO) {
		transactionCreateDTOValidation.validateAndThrow( transactionCreateDTO );

		Optional<Account> account = accountRepository.findById( transactionCreateDTO.getAccountId() );

		if (account.isEmpty())
			throw new ValidationException( "accountId", "not founded" );

		Transaction transaction = Transaction.builder()
				.account( account.get() )
				.operationType( OperationType.getByCode( transactionCreateDTO.getOperationTypeId() ) )
				.amount( transactionCreateDTO.getAmount() )
				.build();
		Transaction transactionPersisted = transactionRepository.save( transaction );

		return toDTO(transactionPersisted);
	}
}
