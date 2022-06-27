package com.spring.bankingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.bankingapp.model.User;
import com.spring.bankingapp.model.UserAccount;

@Repository
public interface AccountRepository extends JpaRepository<UserAccount, Long> {

	List<UserAccount> findByUser(User currentUser);
	UserAccount findByAccountNumber(long accountNumber);
}
