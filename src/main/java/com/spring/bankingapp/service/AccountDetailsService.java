package com.spring.bankingapp.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.bankingapp.model.TransactionResponse;
import com.spring.bankingapp.model.TransferRequest;
import com.spring.bankingapp.model.User;
import com.spring.bankingapp.model.UserAccount;
import com.spring.bankingapp.repository.AccountRepository;

@Service
public class AccountDetailsService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	TransactionDetailsService transactionDetailsService;

	public UserAccount findUserAccount(String username) {
		User currentUser = customUserDetailsService.getUser(username);
		UserAccount account = null;
		List<UserAccount> accounts = accountRepository.findByUser(currentUser);
		if (accounts != null && accounts.size() > 0) {
			account = accounts.get(0);
		}
		return account;
	}

	public UserAccount findUserAccountByNumber(long accountNumber) {
		return accountRepository.findByAccountNumber(accountNumber);
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public TransactionResponse deposit(TransferRequest transferRequest, String userName) {
		// take the transfer request and deposit the money into user account
		UserAccount account = findUserAccount(userName);
		BigDecimal deposit = transferRequest.getAmount();

		if(account!=null && isValid(deposit)) {
			//populate the transfer request with account number
			transferRequest.setDestinationAccountNumber(account.getAccountNumber());
			BigDecimal balance = account.getAvailableAmount();			
			BigDecimal newBalance = balance.add(deposit);
			account.setAvailableAmount(newBalance);
			//update the available balance with deposit and save
			accountRepository.save(account);
			//create new transaction with the transfer request
			transactionDetailsService.createTransactions(transferRequest);
			return new TransactionResponse("Success", "Success", "200");
		}		
		return new TransactionResponse("Failure", "Transaction failed due to invalid parameters", "400");
		
	}

	private boolean isValid(BigDecimal deposit) {
		boolean isValid = false;
		if(deposit.signum() > 0) {
			isValid = true;
		}
		return isValid;
	}
	
	@Transactional(readOnly = false, isolation=Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	public TransactionResponse transfer(TransferRequest transferRequest, String userName) {
		UserAccount sourceAccount = findUserAccount(userName);
		BigDecimal deposit = transferRequest.getAmount();
		UserAccount destinationAccount = findUserAccountByNumber(transferRequest.getDestinationAccountNumber());
		if(destinationAccount !=null && sourceAccount!=null && isValid(deposit) && sourceAccount.getAvailableAmount().compareTo(deposit) >0) {
			//fetching and updating the source account balance
			BigDecimal balance = sourceAccount.getAvailableAmount();			
			BigDecimal newBalance = balance.subtract(deposit);
			sourceAccount.setAvailableAmount(newBalance);
			transferRequest.setSourceAccountNumber(sourceAccount.getAccountNumber());
			//update the available balance with deposit and save
			accountRepository.save(sourceAccount);
			
			//fetching and updating the destination account balance
			BigDecimal destinationBalance = destinationAccount.getAvailableAmount();			
			BigDecimal newDestinationBalance = destinationBalance.add(deposit);
			destinationAccount.setAvailableAmount(newDestinationBalance);
			//update the available balance with deposit and save
			accountRepository.save(destinationAccount);
			
			//create new transactions with the transfer request
			transactionDetailsService.createTransactions(transferRequest);
			
			return new TransactionResponse("Success", "Success", "200");
		}else {
			return new TransactionResponse("Failure", "Transaction failed due to incorrect details", "400");
		}
		
	}
}
