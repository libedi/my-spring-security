package com.libedi.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.libedi.myproject.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByUserId(String userId);
}
