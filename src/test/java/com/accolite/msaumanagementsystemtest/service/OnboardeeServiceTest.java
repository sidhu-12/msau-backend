package com.accolite.msaumanagementsystemtest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accolite.msaumanagement.dao.OnboardeeDAO;
import com.accolite.msaumanagement.model.Onboardee;
import com.accolite.msaumanagement.service.OnboardeeService;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = OnboardeeServiceTest.class)

public class OnboardeeServiceTest {

	@InjectMocks
	OnboardeeService service;
	
	@Mock
	 OnboardeeDAO onboardeerepo;
	@Test
	public void createRecordSuccessTest() throws Exception{
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Creation Done Successfully");
		Onboardee newOnboardee = new Onboardee();
		String returnValue = "Creation Done Successfully";
		when(onboardeerepo.create(newOnboardee)).thenReturn(returnValue);
		assertEquals(responseMap, service.createRecord(newOnboardee));
	}
	@Test
	public void createRecordFailureTest() throws Exception{
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Creation failed. Please check the email");
		Onboardee newOnboardee = new Onboardee("DummyName",null,0,0,null,null,null,null,null);
		String returnValue = "Creation failed. Please check the email";
		when(onboardeerepo.create(newOnboardee)).thenReturn(returnValue);
		assertEquals(responseMap, service.createRecord(newOnboardee));
	}
	@Test
	public void deleteRecordSuccessTest() throws Exception{
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Deletion successfully done");
		String email ="dummy@abc.com";
		String returnValue = "Deletion successfully done";
		when(onboardeerepo.delete(email)).thenReturn(returnValue);
		assertEquals(responseMap, service.deleteRecord(email));
	}
	@Test
	public void deleteRecordFailureTest() throws Exception{
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","No records found.. So No deletion done");
		String email ="dummy@abc.com";
		String returnValue = "No records found.. So No deletion done";
		when(onboardeerepo.delete(email)).thenReturn(returnValue);
		assertEquals(responseMap, service.deleteRecord(email));
	}
	@Test
	public void updateRecordSuccessTest() throws Exception{
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Updation successfully done");
		String email ="dummy@abc.com";
		Map<String, List<String>> dummyUpdateList = new HashMap<>();
		List <String>emailEntry = new LinkedList<String>();
		emailEntry.add(email);
		dummyUpdateList.put("email",emailEntry); //to avoid null exception
		List<String> dummyAttributeList = new LinkedList<String>();
		List<String> dummyValueList = new LinkedList<String>();
		dummyUpdateList.put("attributes",dummyAttributeList);
		dummyUpdateList.put("values",dummyValueList);
		String returnValue = "Updation successfully done";
		when(onboardeerepo.update(dummyAttributeList, dummyValueList, email)).thenReturn(returnValue);
		assertEquals(responseMap, service.updateRecord(dummyUpdateList));
	}
	@Test
	public void updateRecordFailureTest() throws Exception{
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Updation has not been done because onboardee is not found");
		String email ="dummy@abc.com";
		Map<String, List<String>> dummyUpdateList = new HashMap<>();
		List <String>emailEntry = new LinkedList<String>();
		emailEntry.add(email);
		dummyUpdateList.put("email",emailEntry); //to avoid null exception
		List<String> dummyAttributeList = new LinkedList<String>();
		List<String> dummyValueList = new LinkedList<String>();
		dummyUpdateList.put("attributes",dummyAttributeList);
		dummyUpdateList.put("values",dummyValueList);
		String returnValue = "Updation has not been done because onboardee is not found";
		when(onboardeerepo.update(dummyAttributeList, dummyValueList, email)).thenReturn(returnValue);
		assertEquals(responseMap, service.updateRecord(dummyUpdateList));
	}
	@Test
	public void listRecordByAttributeTest() throws Exception{
		String attribute = "location";
		List<Object> dummyValues = new LinkedList<>();
		dummyValues.add("Chennai");
		dummyValues.add("Bangalore");
		when(onboardeerepo.listAllByAttributes(attribute)).thenReturn(dummyValues);
		assertEquals(2, service.listRecordByAttributeList(attribute).size());
		
	}
	@Test
	public void searchRecordByAttributeTest() throws Exception{
		String attribute = "location";
		String value ="Chennai";
		Map<String ,String> dummyMap = new HashMap<String, String>();
		dummyMap.put("attribute",attribute);
		dummyMap.put("value",value);
		List<Onboardee> dummyValues = new LinkedList<>();
		dummyValues.add(new Onboardee());
		dummyValues.add(new Onboardee());
		when(onboardeerepo.listAll(attribute, value)).thenReturn(dummyValues);
		assertEquals(2, service.searchResultByAttribute(dummyMap).size());
		
	}
}
