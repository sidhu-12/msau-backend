package com.accolite.msaumanagementsystemtest.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.accolite.msaumanagement.utility.SecurePasswordGeneration;



@RunWith(SpringRunner.class)
@SpringBootTest( classes = SecurePasswordGenerationTest.class)
public class SecurePasswordGenerationTest {
	@InjectMocks
	SecurePasswordGeneration generator;
	@Test
	public void get_SHA_512_SecurePasswordTest() throws Exception
	{
		String passwordString = "dummyPassword";
		String saltString = "exampleSalt";
		assertEquals("de8913e708d08a350205b1baa51ac00b45699700c746edd0565c7d152de589e04ce8a838cc7aa55fffdc225c20f3f808bd9fd53b87fe140d038b4d79e2664d7a"
				,generator.get_SHA_512_SecurePassword(passwordString, saltString,"SHA-512"));
		
		
	}
	@Test
	public void get_SHA_512_SecurePasswordExceptionTest()
	{
		String passwordString = "dummyPassword";
		String saltString = "exampleSalt";
		NoSuchAlgorithmException exception = assertThrows(NoSuchAlgorithmException.class, 
				()->generator.get_SHA_512_SecurePassword(passwordString, saltString,"SHA-221"));
		assertEquals("Give Appropriate Algorithm", exception.getMessage());
		
		
	}
}
