package com.spring.bankingapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.bankingapp.model.TransactionData;
import com.spring.bankingapp.model.TransactionStatus;
import com.spring.bankingapp.model.TransactionType;
import com.spring.bankingapp.model.TransferRequest;
import com.spring.bankingapp.model.UserAccount;
import com.spring.bankingapp.model.UserTransaction;
import com.spring.bankingapp.repository.TransactionRepository;

@Service
public class TransactionDetailsService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	AccountDetailsService accountDetailsService;
	
	public List<TransactionData> getTransactions(String username){
		UserAccount account = accountDetailsService.findUserAccount(username);
		List<TransactionData> transactionDetails = new ArrayList<TransactionData>();
		if(account !=null) {
			List<UserTransaction> transactions = transactionRepository.findByAccountNumber(account.getAccountNumber());		
			if(transactions.size() > 0) {				
				transactions.stream().forEach(transaction -> {
					TransactionData TransactionData = new TransactionData();
					BeanUtils.copyProperties(transaction, TransactionData);
					transactionDetails.add(TransactionData);
				});
			}
		}
		
		return transactionDetails;
	}
	
	public void createTransactions(TransferRequest transfer) {
		
		List<UserTransaction> transactions = fetchTransactions(transfer);
		transactionRepository.saveAll(transactions);
	}

	private List<UserTransaction> fetchTransactions(TransferRequest transfer) {
		List<UserTransaction> transactions = new ArrayList<UserTransaction>();
		
		if(transfer.getDestinationAccountNumber() > 0) {
			UserTransaction creditTransaction = new UserTransaction();
			populateTransaction(creditTransaction, transfer);
			creditTransaction.setAccountNumber(transfer.getDestinationAccountNumber());
			creditTransaction.setTransactionType(TransactionType.CREDIT);
			transactions.add(creditTransaction);
		}
		
		if(transfer.getSourceAccountNumber() > 0) {
			UserTransaction debitTransaction = new UserTransaction();
			populateTransaction(debitTransaction, transfer);
			debitTransaction.setAccountNumber(transfer.getSourceAccountNumber());
			debitTransaction.setTransactionType(TransactionType.DEBIT);
			transactions.add(debitTransaction);
		}		

		return transactions;
	}
	
	private UserTransaction populateTransaction(UserTransaction transaction, TransferRequest transfer) {
		transaction.setAmount(transfer.getAmount());
		transaction.setCurrencyCode(transfer.getCurrency());
		transaction.setTransactionDate(LocalDateTime.now());
		transaction.setTransactionStatus(TransactionStatus.SUCCESS);
		return transaction;
		
	}	
}
