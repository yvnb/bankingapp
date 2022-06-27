package com.spring.bankingapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;
    
    @Column
    @NotNull(message = "account number cannot be null")
    private long accountNumber;
    
    @Column
    @NotNull(message = "amount cannot be null")
    private BigDecimal availableAmount;
    
    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;
    
    @Column
    @NotNull(message = "currency code cannot be null")
    private String currencyCode;
    
    
}
