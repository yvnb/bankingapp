package com.spring.bankingapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class UserTransaction {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    
    @Column
    @NotNull(message = "account number cannot be null")
    private long accountNumber;
    
    @Column
    @NotNull(message = "amount cannot be null")
    private BigDecimal amount;
    
    @Column
    @NotNull(message = "transaction date is mandatory")
    private LocalDateTime transactionDate;
    
    @Column
    @NotNull(message = "transaction status cannot be null")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    
    @Column
    @NotNull(message = "transaction type cannot be null")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    	
    @Column
    @NotNull(message = "currency code cannot be null")
    private String currencyCode;
}
