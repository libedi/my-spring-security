package com.libedi.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.libedi.myproject.domain.Account;
import com.libedi.myproject.service.HelloMessageService;

/**
 * Home Controller
 * @author libedi
 *
 */
@Controller
public class HomeController {

	@Autowired
	private HelloMessageService helloMessageService;
	/**
	 * Index page
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(){
		return "index";
	}
	
	/**
	 * ADMIN 권한 또는 /getPrivateMessage?userId= 가 로그인 아이디와 같을 경우
	 * @param account
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getPrivateMessage")
	@PreAuthorize("(#account.userId == principal.Username) or hasAuthority('ADMIN')")
	public String authstring(Account account, Model model){
		model.addAttribute("msg", "당신은 관리자이거나 요청 파라미터랑 아이디가 같습니다.");
		return "authorizedMessage";
	}
	
	/**
	 * USER 권한인 경우
	 * @param account
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getUserMessage")
	@PreAuthorize("hasAuthority('USER')")
	public String userMessage(Account account, Model model){
		model.addAttribute("msg", "당신은 유저권한입니다.");
		return "authorizedMessage";
	}
	
	@RequestMapping(value = "/403")
	public void accessDenied(){}
	
	@GetMapping(value = "/message")
	@ResponseBody
	public String getMessage(){
		return this.helloMessageService.getMessage();
	}
	
}
