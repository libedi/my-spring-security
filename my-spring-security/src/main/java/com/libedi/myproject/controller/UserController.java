package com.libedi.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User Controller
 * @author libedi
 *
 */
@Controller
public class UserController {
	
	@RequestMapping(value = "/user")
	public void user(){}
}
