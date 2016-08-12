package com.libedi.myproject.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.libedi.myproject.domain.Account;

/**
 * 인증된 정보인 Principal에 들어갈 클래스
 * - UserDetails 인터페이스를 구현하거나
 * - User 클래스를 상속해야 한다. 
 * @author libedi
 *
 */
public class UserDetailsImpl extends User {

	private static final long serialVersionUID = 3078247060946311092L;
	
	private String nick;

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public UserDetailsImpl(Account account){
		super(account.getUserId(), account.getPassword(), authorities(account));
		this.setNick(account.getNick());
	}

	private static Collection<? extends GrantedAuthority> authorities(Account account) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(account.getRole()));
		return authorities;
	}
}
