package com.accolite.msaumanagementsystemtest.service;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.accolite.msaumanagement.dao.UserLoginDAO;
import com.accolite.msaumanagement.model.UserLogin;
import com.accolite.msaumanagement.service.LoginService;


@RunWith(SpringRunner.class)
@SpringBootTest( classes = LoginServiceTest.class)
public class LoginServiceTest {

	@InjectMocks
	LoginService service;
	
	@Mock
	 UserLoginDAO userLoginrepo;
	@Test
	public void authenticateFailureTest() throws Exception {
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("responseMessage","Login Unsuccessful : Password incorrect");
			responseMap.put("name" ,"");
			String userName = "sid123@abc.com";
			String password = "abcccdef";
			String []returnValue = {"Login Unsuccessful : Password incorrect",""};
			
			when(userLoginrepo.authenticate(userName,password)).thenReturn(returnValue);
			assertEquals(responseMap, service.auth(userName, password));
			
			
		
		
	}
	@Test
	public void authenticateUserNotRegisteredTest() throws Exception {
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("responseMessage","User's email is  not registered yet");
			responseMap.put("name" ,"");
			String userName = "asd@abc.com";
			String password = "abcdef";
			String []returnValue = {"User's email is  not registered yet",""};
			
			when(userLoginrepo.authenticate(userName,password)).thenReturn(returnValue);
			assertEquals(responseMap, service.auth(userName, password));
			
		
		
	}

	@Test
	public void authenticateSuccessTest() throws Exception
	{
		
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("responseMessage","Login Successfully");
			responseMap.put("name" ,"Sid");
			String userName = "sid123@abc.com";
			String password = "abcdef";
			String []returnValue = {"Login Successfully","Sid"};
			
			when(userLoginrepo.authenticate(userName,password)).thenReturn(returnValue);
			assertEquals(responseMap, service.auth(userName, password));
			
			
		
	
	}
	@Test
	public void registerSuccessTest() throws Exception{
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("responseMessage","Registered Successfully");
			UserLogin user = new UserLogin("Tharani","tharani@accolitedigital.com","abcdef_1234",null,false);
			boolean returnValue = true;
			
			when(userLoginrepo.registerUser(user)).thenReturn(returnValue);
			assertEquals(responseMap, service.register(user));
			
			
		
		
	}
	@Test
	public void registerFailureTest() throws Exception{
		
			Map<String, String> responseMap = new HashMap<>();
			responseMap.put("responseMessage","Registration Unsuccessful");
			UserLogin user = new UserLogin("Sid","sid@abc.com","abcdef_1234",null,false);
			boolean returnValue = false;
			
			when(userLoginrepo.registerUser(user)).thenReturn(returnValue);
			assertEquals(responseMap, service.register(user));
			
			
	}
	
	
}
