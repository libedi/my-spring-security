package com.libedi.myproject.service;

import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MessageServiceTest {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private HelloMessageService messageService;
	
	@Before
	public void setUp(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).alwaysDo(MockMvcResultHandlers.print()).apply(springSecurity()).build();
	}
	
	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void 인증되지않은사용자는_AuthenticationCredentialsNotFoundException을_발생시킨다(){
		this.messageService.getMessage();
	}
	
	@Test
	@WithMockUser
	public void getMessageWithMockUser(){
		String message = this.messageService.getMessage();
		System.out.println("받은 메시지" + message);
		assertNotNull(message);
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = {"ADMIN", "USER"})
	public void getMessageWithMockUserCustomAuthorities(){
		String message = this.messageService.getMessage();
		System.out.println("어드민으로 받은 메시지" + message);
		assertNotNull(message);
	}
	
	@Test
	public void mvcAdminTest() throws Exception{
		ResultActions result = this.mockMvc.perform(get("/admin").with(user("admin").password("1234").authorities(() -> "USER", () -> "ADMIN")));
		result.andDo(print());
		result.andExpect(status().isOk());
	}

}
