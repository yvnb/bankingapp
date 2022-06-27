package com.spring.bankingapp.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {

	private long destinationAccountNumber;
	private BigDecimal amount;
	private String currency;
	private long sourceAccountNumber;
}
