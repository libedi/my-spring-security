package com.libedi.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Admin Controller
 * @author libedi
 *
 */
@Controller
public class AdminController {

	@RequestMapping(value = "/admin")
	public void admin(){}
}
