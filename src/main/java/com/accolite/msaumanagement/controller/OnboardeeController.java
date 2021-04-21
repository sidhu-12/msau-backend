package com.accolite.msaumanagement.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.msaumanagement.model.Onboardee;
import com.accolite.msaumanagement.service.OnboardeeService;

@RestController
@Validated

public class OnboardeeController {
	private static final Logger logger = LogManager.getLogger(OnboardeeController.class);
	@Autowired
	OnboardeeService service;

	 
	   @PostMapping("/create") 
	   public ResponseEntity<Map<String,String>> register(@RequestBody Onboardee newOnboardee)  throws Exception {
		   logger.info("Accessing the Create record mapping");
		   return ResponseEntity.ok(service.createRecord(newOnboardee));

	   }
	   
	   @DeleteMapping("/delete") 
	   public ResponseEntity<Map<String,String>> delete(@RequestParam(value ="deleteEmail")String email) throws Exception  {
		   logger.info("Accessing the Delete Record mapping");
	      return ResponseEntity.ok(service.deleteRecord(email));
	   }
	   @PutMapping("/update")
	   public ResponseEntity<Map<String, String>> update(@RequestBody Map<String, List<String>> updateRequest) throws Exception {
		   logger.info("Accessing the Update Record Mapping");
		   return ResponseEntity.ok(service.updateRecord(updateRequest));
	   }
	   @GetMapping("/listByAttribute")
	   public ResponseEntity<List<Object>> listByAttribute(@RequestParam(value = "attribute")String attribute) throws Exception{
		   logger.info("Accessing the List By Attribute mapping");
		   return ResponseEntity.ok(service.listRecordByAttributeList(attribute));
	   }
	   @PostMapping("/searchResult")
	   public ResponseEntity<List<Onboardee>> searchResult(@RequestBody Map<String, String> attributeValueMap)  throws Exception {
		   logger.info("Accessing the Search record mapping");
		   return ResponseEntity.ok(service.searchResultByAttribute(attributeValueMap));

	   }
	}


