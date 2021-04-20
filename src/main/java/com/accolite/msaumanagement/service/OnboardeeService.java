package com.accolite.msaumanagement.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.msaumanagement.dao.OnboardeeDAO;
import com.accolite.msaumanagement.model.Onboardee;

@Service
public class OnboardeeService {
	private static final Logger logger = LogManager.getLogger(OnboardeeService.class);

	@Autowired
	OnboardeeDAO onboardeeRepo;
	@Transactional
	public Map<String , String > createRecord(Onboardee newOnboardee) throws Exception
	{
		logger.info(" Creation of record service started");
		String response = onboardeeRepo.create(newOnboardee);
		Map<String , String> responseObject = new HashMap<>();
		responseObject.put("responseMessage", response);
		logger.info(" Creation of record service ended");
		return responseObject;
	}
	@Transactional
	public Map<String , String > deleteRecord(String email) throws Exception
	{
		logger.info(" Deletion of record service started");
		String response = onboardeeRepo.delete(email);
		Map<String , String> responseObject = new HashMap<>();
		responseObject.put("responseMessage", response);
		logger.info(" Deletion of record service ended");
		return responseObject;
	}
	
}
