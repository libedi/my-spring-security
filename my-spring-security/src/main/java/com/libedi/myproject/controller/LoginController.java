package com.libedi.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Login Controller
 * @author libedi
 *
 */
@Controller
public class LoginController {
	
	/**
	 * Login page
	 */
	@RequestMapping(value = "/login")
	public void login(){}
	
}
