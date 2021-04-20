package com.accolite.msaumanagement.service;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.accolite.msaumanagement.dao.UserLoginDAO;
import com.accolite.msaumanagement.model.UserLogin;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
@Service
public class LoginService {
	
	private static final Logger logger = LogManager.getLogger(LoginService.class);

	@Autowired
	UserLoginDAO userLoginRepo; //User login repo
	@Transactional
	public Map<String,String> auth(String userName , String password) throws Exception
	{
		logger.info(" Authenticate Service Running....");
		String[] res = userLoginRepo.authenticate(userName, password);
		Map <String,String> responseObject = new HashMap<>();
		responseObject.put("responseMessage",res[0]);
		responseObject.put("name" , res[1]);
		logger.info(" Authenticate Service Finished   Message: " + res[0]);
		return responseObject;
		
	}
	@Transactional
	public Map<String,String> register(UserLogin user) throws Exception
	{
		logger.info(" Register Service Running....");
		boolean success = userLoginRepo.registerUser(user);
		Map <String,String> responseObject = new HashMap<>();
		if(success == true)
		{
			responseObject.put("responseMessage", "Registered Successfully");
		}
		else
		{
			responseObject.put("responseMessage", "Registered Unsuccessful");
		}
		logger.info(" Register Service Finished");
		return responseObject;
		
	}
	

}
