package com.spring.bankingapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

	private String transactionStatus;
	private String message;
	private String statusCode;
	
}
