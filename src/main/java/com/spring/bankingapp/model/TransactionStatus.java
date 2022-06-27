package com.spring.bankingapp.model;

import lombok.Getter;

@Getter
public enum TransactionStatus {
	FAILED, REFUND, SUCCESS, CANCELLED;
}
