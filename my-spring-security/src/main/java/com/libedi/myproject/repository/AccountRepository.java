package com.libedi.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.libedi.myproject.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUserId(String userId);
	
	@Query("select a from Account a where a.userId = ?#{ principal.Username }")
	Account findMe();
}
