package com.accolite.msaumanagementsystemtest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
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

import com.accolite.msaumanagement.controller.OnboardeeController;
import com.accolite.msaumanagement.exception.GlobalExceptionHandler;
import com.accolite.msaumanagement.model.Onboardee;
import com.accolite.msaumanagement.service.OnboardeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = OnboardeeControllerTest.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class OnboardeeControllerTest {

	MockMvc mock;
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	 OnboardeeService service;
	
	@InjectMocks
	OnboardeeController controller;
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mock = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new GlobalExceptionHandler()).build();


	}
	@Test
	public void create() throws Exception {
		String uri = "/create";
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Creation Done Successfully");
		Onboardee newOnboardee = new Onboardee();
		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(newOnboardee);
		when(service.createRecord(newOnboardee)).thenReturn(responseMap);
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void delete() throws Exception {
		String uri = "/delete";
		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("responseMessage","Deletion successfully done");
		String email ="dummy@abc.com";
		when(service.deleteRecord(email)).thenReturn(responseMap);
//		ObjectMapper objectMapper = new ObjectMapper();
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.delete(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.param("deleteEmail",email)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void update() throws Exception {
		String uri = "/update";
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
		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(dummyUpdateList);
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void listByAttribute() throws Exception {
		String uri = "/listByAttribute";
		String attribute = "location";
		List<Object> dummyValues = new LinkedList<>();
		dummyValues.add("Chennai");
		dummyValues.add("Bangalore");
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.param("attribute", attribute)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
	@Test
	public void searcRecordByAttribute() throws Exception {
		String uri = "/searchResult";
		String attribute = "location";
		String value ="Chennai";
		Map<String ,String> dummyMap = new HashMap<String, String>();
		dummyMap.put("attribute",attribute);
		dummyMap.put("value",value);
		ObjectMapper objectMapper = new ObjectMapper();
		String inputJson = objectMapper.writeValueAsString(dummyMap);
		MvcResult mvcResult = mock.perform(MockMvcRequestBuilders.post(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.param("attribute", attribute)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson))
				.andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
