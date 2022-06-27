package com.spring.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.bankingapp.model.UserTransaction;

@Repository
public interface TransactionRepository extends JpaRepository<UserTransaction, Long>{

	List<UserTransaction> findByAccountNumber(long accountNumber);
}
