package com.spring.bankingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bankingapp.model.TransactionData;
import com.spring.bankingapp.service.TransactionDetailsService;


@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
	
	@Autowired
	TransactionDetailsService transactionDetailsService;
	
	@GetMapping
	public List<TransactionData> fetchTransactions(Authentication authentication) {
		return transactionDetailsService.getTransactions(authentication.getName());
	}
	
	
}
