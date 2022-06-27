package com.spring.bankingapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionData {


	    private long transactionId;	    
	    private long accountNumber;
	    private BigDecimal amount;
	    private LocalDateTime transactionDate;
	    private TransactionStatus transactionStatus;
	    private TransactionType transactionType;
	    private String currencyCode;
}
