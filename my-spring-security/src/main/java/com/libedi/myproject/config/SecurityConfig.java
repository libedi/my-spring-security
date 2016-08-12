package com.libedi.myproject.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Spring Security Configuration
 * @author libedi
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;		// JDBC 로그인을 위한 dataSource
	
	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * Authentication Configuration
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 1. In-Memory login configuration
//		auth.inMemoryAuthentication().withUser("admin").password("1234").roles("ADMIN");
		
		// 2. JDBC login configuration
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.rolePrefix("");		// ROLE_ prefix를 초기화
		
		// 3. Customizing login configuration
		auth.userDetailsService(userDetailsService);
	}

	/**
	 * Http Request Configuration
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**")
					.hasAuthority("ADMIN")
//					.hasRole("ADMIN")		// DB에서 ROLE_ prefix를 삭제하려면 hasRole() 대신에 hasAuthority()를 사용한다.
				.antMatchers("/user/**").hasAnyAuthority("ADMIN", "USER")
				.antMatchers("/**").permitAll()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll();
	}

	
	
}
