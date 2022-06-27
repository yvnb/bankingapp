package com.spring.bankingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bankingapp.model.TransactionResponse;
import com.spring.bankingapp.model.TransferRequest;
import com.spring.bankingapp.service.AccountDetailsService;


@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	AccountDetailsService accountDetailsService ;
	
	@PostMapping("/deposit")
	public TransactionResponse deposit(@RequestBody TransferRequest transferRequest, Authentication authentication) {
		
		return accountDetailsService.deposit(transferRequest, authentication.getName());
	}
	
	@PostMapping("/transfer")
	public TransactionResponse transfer(@RequestBody TransferRequest transferRequest, Authentication authentication) {
		return accountDetailsService.transfer(transferRequest, authentication.getName());
	}
	
	
}
