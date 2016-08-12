package com.libedi.myproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.libedi.myproject.domain.Account;
import com.libedi.myproject.repository.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		Account account = this.accountRepository.findByUserId(userId);
		if(account == null){
			throw new UsernameNotFoundException(userId);
		}
		return new UserDetailsImpl(account);
	}

}
