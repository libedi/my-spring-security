package com.libedi.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.libedi.myproject.domain.Account;
import com.libedi.myproject.repository.AccountRepository;
import com.libedi.myproject.security.UserDetailsImpl;

/**
 * User Controller
 * @author libedi
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * User page
	 */
	@RequestMapping(value = "/user")
	public void user(){}
	
	/**
	 * 회원가입 페이지
	 */
	@RequestMapping(value = "/registerForm")
	public void registerForm(){}
	
	/**
	 * 회원가입
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(Account account){
		// 회원정보 데이터베이스 저장
		this.accountRepository.save(account);
		
		// SecurityContextHolder 에서 Context 를 받아 인정 설정
		UserDetails userDetails = new UserDetailsImpl(account);
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/";
	}
}
