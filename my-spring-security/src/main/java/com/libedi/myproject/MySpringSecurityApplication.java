package com.libedi.myproject;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.libedi.myproject.domain.Account;
import com.libedi.myproject.repository.AccountRepository;

@SpringBootApplication
public class MySpringSecurityApplication {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(MySpringSecurityApplication.class, args);
	}
	
	/**
	 * 초기 시작시 데이터 초기화 (JPA : CREATE-DROP)
	 * 참고 : https://www.youtube.com/watch?v=dnCf2-XYXL8
	 * @return
	 */
	@Bean
	public InitializingBean insertFixtureUsers(){
		return () -> {
			Account admin = new Account();
			admin.setUserId("admin");
			admin.setPassword(this.passwordEncoder.encode("1234"));
			admin.setRole("ADMIN");
			admin.setNick("관리자");
			this.accountRepository.save(admin);
			
			Account user = new Account();
			user.setUserId("libedi");
			user.setPassword(this.passwordEncoder.encode("1234"));
			user.setRole("USER");
			user.setNick("리베디");
			this.accountRepository.save(user);
		};
	}
	
	
}
