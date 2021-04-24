package com.accolite.msaumanagement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accolite.msaumanagement.model.UserLogin;
import com.accolite.msaumanagement.service.LoginService;

import java.security.Principal;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


@RestController
@Validated

public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	@Autowired
	LoginService service;

	   @GetMapping("/login") 
	   public ResponseEntity<Map<String,String>> login(@RequestParam("username")String username, @RequestParam("password")String password) throws Exception  {
		   logger.info("Accessing the Login mapping");
	      return ResponseEntity.ok(service.auth(username, password));
	   }
	   @PostMapping("/register") 
	   public ResponseEntity<Map<String,String>> register(@RequestBody UserLogin user)  throws Exception {
		   logger.info("Accessing the Register mapping");
		   return ResponseEntity.ok(service.register(user));

	   }
	   
	}

