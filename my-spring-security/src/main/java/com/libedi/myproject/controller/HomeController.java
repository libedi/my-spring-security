package com.libedi.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Controller
 * @author libedi
 *
 */
@Controller
public class HomeController {

	/**
	 * Index page
	 * @return
	 */
	@RequestMapping(value = "/")
	public String index(){
		return "index";
	}
}
