package com.accolite.msaumanagement.service;

import java.util.HashMap;
import java.util.List;
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
		logger.info(" Creation of record service has been initiated for " +newOnboardee.getEmail());
		String response = onboardeeRepo.create(newOnboardee);
		Map<String , String> responseObject = new HashMap<>();
		responseObject.put("responseMessage", response);
		logger.info(" Creation of record service ended for " +newOnboardee.getEmail());
		return responseObject;
	}
	@Transactional
	public Map<String , String > deleteRecord(String email) throws Exception
	{
		logger.info(" Deletion of record service has been initiated for "+email);
		String response = onboardeeRepo.delete(email);
		Map<String , String> responseObject = new HashMap<>();
		responseObject.put("responseMessage", response);
		logger.info(" Deletion of record service ended  for  "+email);
		return responseObject;
	}
	@Transactional
	public Map<String, String> updateRecord(Map<String,List<String>> updateList) throws Exception
	{
		logger.info("Updation of a Record Service has been initiated for "+ updateList.get("email").get(0));
		List<String> attributeList = updateList.get("attributes");
		List<String> valueList = updateList.get("values");
		String email = updateList.get("email").get(0);
		String responseString  = onboardeeRepo.update(attributeList, valueList, email);
		Map<String , String> responseObject = new HashMap<>();
		responseObject.put("responseMessage", responseString);
		logger.info(" Updation of a Record service ended for "+updateList.get("email").get(0));
		return responseObject;
		
		
	}
	@Transactional
	public List<Object> listRecordByAttributeList(String attribute) throws Exception{
		logger.info("Listing of values by Attribute service has been started for "+attribute);
		List<Object> responseObjectList = onboardeeRepo.listAllByAttributes(attribute);
		logger.info("Listing of values by Attribute service ended  for" +attribute);
		return responseObjectList;
	}
	@Transactional
	public List<Onboardee> searchResultByAttribute(Map<String, String> attributeValueMap) throws Exception{
		logger.info("Searching of values by Attribute service has been started");
		List<Onboardee> respOnboardees =  onboardeeRepo.listAll(attributeValueMap.get("attribute"), attributeValueMap.get("value"));
		logger.info("Searching of values by Attribute service ended ");
		return respOnboardees;
		
	}
}
