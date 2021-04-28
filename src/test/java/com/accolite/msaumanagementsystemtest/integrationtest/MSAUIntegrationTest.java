package com.accolite.msaumanagementsystemtest.integrationtest;

import java.util.HashMap;
import java.util.List;
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
import com.accolite.msaumanagement.model.Onboardee;
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
    public ResponseEntity<HashMap> create(Onboardee newOnboardee) {
	
	HttpEntity<Onboardee> entity = new HttpEntity<Onboardee>(newOnboardee, headers);
    return template.exchange(getBackEndServer()+"/create",HttpMethod.POST,entity,
    		HashMap.class);	
	}	
    public ResponseEntity<HashMap> delete(String email) {
    	
    	HttpEntity<HashMap> entity = new HttpEntity<HashMap>(null, headers);
        return template.exchange(getBackEndServer()+"/delete?deleteEmail="+email,HttpMethod.DELETE,entity,
        		HashMap.class);	
    	}
    public ResponseEntity<HashMap> update(Map<String, List<String>> updateList) {
    	
    	HttpEntity<Map<String, List<String>>> entity = new HttpEntity<Map<String, List<String>>>(updateList, headers);
    	
        return template.exchange(getBackEndServer()+"/update",HttpMethod.PUT,entity,
        		HashMap.class);	
    	}	
    public ResponseEntity<List> listAll(String attribute) {
    	
    	HttpEntity< List<Object>> entity = new HttpEntity<List<Object>>(null, headers);
    	
        return template.exchange(getBackEndServer()+"/listByAttribute?attribute="+attribute,HttpMethod.GET,entity,
        		List.class);	
    	}	
  public ResponseEntity<List> search(Map<String, String> attributeValue) {
    	
    	HttpEntity<Map<String, String>> entity = new HttpEntity<Map<String, String> >(attributeValue, headers);
    	
        return template.exchange(getBackEndServer()+"/searchResult",HttpMethod.POST,entity,
        		List.class);	
    	}
}
