package com.accolite.msaumanagementsystemtest.integrationtest;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import com.accolite.msaumanagement.MSAUManagementApplication;
import com.accolite.msaumanagement.dao.UserLoginDAO;
import com.accolite.msaumanagement.model.UserLogin;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@ActiveProfiles("INTEGRATION_TEST")
@SpringBootTest(classes = MSAUManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MSAUIntegrationTest {
	private final String SERVER_URL = "http://localhost";
	  protected TestRestTemplate template = new TestRestTemplate();
	  @LocalServerPort
		private int port;
	  HttpHeaders headers = new HttpHeaders();
    private String getBackEndServer() {
    
    	headers.add("Accept", "application/json");
        return SERVER_URL + ":" + port;
        
    }
    public ResponseEntity<HashMap> authenticate(String username,String password) {
    	
    	HttpEntity<HashMap> entity = new HttpEntity<HashMap>(null, headers);
        return template.exchange(getBackEndServer()+"/login?username="+username+"&password="+password,HttpMethod.GET,entity,
        		HashMap.class);
    }
public ResponseEntity<HashMap> register(UserLogin user) {
    	
    	HttpEntity<UserLogin> entity = new HttpEntity<UserLogin>(user, headers);
        return template.exchange(getBackEndServer()+"/register",HttpMethod.POST,entity,
        		HashMap.class);
    }
}
