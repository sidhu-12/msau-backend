package com.accolite.msaumanagement.controller;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@SpringBootApplication
@RestController

public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	@GetMapping(value="/")
	public String hello()
	{
		logger.info("Accessing the Default Mapping");
		logger.trace("Show Hello World");
		return "Hello World";
	}

	   @RequestMapping(value = "/login" ) 
	   public ResponseEntity<String> user() {
		   logger.info("Accessing the OAuth Login");
		   logger.trace("Show Hello World");
	      return ResponseEntity.ok("Please Login Here");
	   }
	}

