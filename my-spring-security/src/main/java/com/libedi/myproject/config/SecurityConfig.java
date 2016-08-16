package com.libedi.myproject.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * Spring Security Configuration
 * @author libedi
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String REMEMBER_ME_KEY = "rememberMeKey";
	
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
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(this.passwordEncoder());		// PasswordEncoder Configuration
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
				.loginPage("/login").permitAll()
				.and()
			.rememberMe()
//				.key("rememberKey").rememberMeParameter("rememberMeParameter")		// 1. Default Remember-Me Configuration : TokenBasedRememberMeServices
//				.key(this.REMEMBER_ME_KEY).rememberMeServices(this.tokenBasedRememberMeServices())		// 2. Custom Remember-Me Configuration : Cookie Type
				.key(this.REMEMBER_ME_KEY).rememberMeServices(this.persistentTokenBasedRememberMeServices())	// 3. Custom Remember-Me Configuration : Persistence Type 
				;
	}
	
	/**
	 * Custom TokenBasedRememberMeServices Bean Configuration
	 * @return TokenBasedRememberMeServices
	 */
	@Bean
	public TokenBasedRememberMeServices tokenBasedRememberMeServices(){
		TokenBasedRememberMeServices tokenBasedRememberMeServices =
				new TokenBasedRememberMeServices(this.REMEMBER_ME_KEY, this.userDetailsService);
		tokenBasedRememberMeServices.setCookieName("rememberMeCookie");
		tokenBasedRememberMeServices.setParameter("rememberMeParameter");
		return tokenBasedRememberMeServices;
	}
	
	/**
	 * PersistentTokenBasedRememberMeServices Bean Configuration
	 * @return PersistentTokenBasedRememberMeServices
	 */
	@Bean
	public PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices(){
		PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices =
				new PersistentTokenBasedRememberMeServices(this.REMEMBER_ME_KEY, this.userDetailsService, jdbcTokenRepository());
		persistentTokenBasedRememberMeServices.setParameter("rememberMeParameter");
		return persistentTokenBasedRememberMeServices;
	}

	/**
	 * JdbcTokenRepositoryImpl Bean Configuration for creating PersistentTokenRepository
	 * @return JdbcTokenRepositoryImpl
	 */
	@Bean
	public JdbcTokenRepositoryImpl jdbcTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setCreateTableOnStartup(false);
		jdbcTokenRepository.setDataSource(this.dataSource);
		return jdbcTokenRepository;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
}
