package com.accolite.msaumanagementsystemtest.controller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.accolite.msaumanagement.controller.LoginController;
import com.accolite.msaumanagement.dao.UserLoginDAO;
import com.accolite.msaumanagement.exception.GlobalExceptionHandler;
import com.accolite.msaumanagement.model.UserLogin;
import com.accolite.msaumanagement.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;




@SpringBootTest(classes = LoginControllerTest.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class LoginControllerTest {

	MockMvc mock;
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	 LoginService service;
	@MockBean
	 UserLoginDAO repo;
	
	@InjectMocks
	LoginController controller;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();


	}
	@Test
	public void login() throws Exception {
		String uri = "/login";
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Login Successfully");
		responseMap.put("name" ,"Sid");
		String userName = "sid123@abc.com";
		String password = "abcdef";
		when(service.auth(userName, password)).thenReturn(responseMap);
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get(uri).param("username", userName).param("password", password)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void register() throws Exception {
		String uri = "/register";
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Registered Successfully");
		UserLogin user = new UserLogin("Tharani","tharani@accolitedigital.com","abcdef_1234",null,false);
		when(service.register(user)).thenReturn(responseMap);
		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(user);
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
