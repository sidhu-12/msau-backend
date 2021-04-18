package com.accolite.msaumanagement.dao;
import java.util.UUID;

import com.accolite.msaumanagement.dao.UserLoginDAO;
import com.accolite.msaumanagement.model.UserLogin;
import com.accolite.msaumanagement.utility.SecurePasswordGeneration;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.apache.logging.log4j.LogManager;
@Repository
public class UserLoginDAO {
	
	  private static final Logger logger = LogManager.getLogger(UserLoginDAO.class);
	 
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  @Autowired
	  SecurePasswordGeneration generator;
	  
	public String[] authenticate(String userName, String password) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Entering Authentication ");
		String[] message = {""}; // array is used for lambda expression
		try {
			
			String sql = "SELECT pwdhash , salt from user_login where BINARY email = ?"; 
		
			jdbcTemplate.query(sql, new Object[] { userName }, 
					(rs , rowNum) -> new UserLogin(null,rs.getString(1),null,rs.getString(2),false)).forEach(user->{
						String pwdOriginal = user.getPassword();
						String pwdGenerated = generator.get_SHA_512_SecurePassword(password, user.getSalt());
						System.out.print(user.getPassword());
					if(user.getPassword().equals(""))
					{
						message[0] = "User not registered yet";
					}
					else if(pwdOriginal.equals(pwdGenerated) == true)
					{
						
						message[0] = "Login Successfully" ;
					}
					else
					{
						message[0] = "Login Unsuccessful : Password incorrect" ;
					}
				
			});
			if(message[0].equals("") == true)
			{
				message[0] = "User's email is  not registered yet ";
			}
		return message;
				
			
		}
		catch(Exception e)
		{
			logger.error("Failed Login Authentication"+e.toString());
			
		}
		
		return message;
		
	}

	public  boolean registerUser(UserLogin user) {
		// TODO Auto-generated method stub
		logger.info("Entering RegisterUser ");
		
		logger.info("Generating Secure Password");
		String salt = UUID.randomUUID().toString();
		String pwdHash =  generator.get_SHA_512_SecurePassword(user.getPassword() , salt);
		
		logger.info("Generated Secure Password");
		
		try {
			String sql = "INSERT INTO user_login(name,email,pwdhash,salt,OAuth) VALUES(?,?,?,?,?)";
			jdbcTemplate.update(sql, user.getName(),user.getEmail(),pwdHash,salt,user.isOAuth());		
			}
		catch(Exception e)
		{
			logger.error("Registration Unsuccessful:"+e.toString());
			return false;
		}
		return true;
	}

}
