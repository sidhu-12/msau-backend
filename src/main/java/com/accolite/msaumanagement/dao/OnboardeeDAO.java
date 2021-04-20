package com.accolite.msaumanagement.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.msaumanagement.exception.CustomExceptionHandler;
import com.accolite.msaumanagement.model.Onboardee;

@Repository
public class OnboardeeDAO {
	 private static final Logger logger = LogManager.getLogger(OnboardeeDAO.class);
	 
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  
	  public String create(Onboardee newOnboardee) throws Exception{
		  logger.info("Creating the onboardee");
		  String message ="";
		  try {
			  String sql = "INSERT INTO onboardee_details(email,name,phone_no,demand_id,location,skills,start_date,background_check_status,onboard_status)"
			  		+ " VALUES(?,?,?,?,?,?,?,?,?)";
			  jdbcTemplate.update(sql, 
				  newOnboardee.getEmail(),
				  newOnboardee.getName(),
				  newOnboardee.getPhoneNo(),
				  newOnboardee.getDemandId(),
				  newOnboardee.getLocation(),
				  newOnboardee.getSkills(),
				  newOnboardee.getStartDate(),
				  newOnboardee.getBackgroundCheckStatus(),
				  newOnboardee.getOnBoardStatus()
				  
				  );
		  }catch(JDBCException e)
		  {
			  logger.error("Failed at creation of onboardee "+e.toString());
			  message = "Creation failed. Please check the email";
			  return message;
		  }
		  catch(Exception e)
		  {
			  logger.error("Failed at creation of onboarding other issuses "+e.toString());
			  throw new CustomExceptionHandler("Creation of Onboardee gone wrong");
		  }
		  message = "Creation Done Successfully";
		  logger.info("Creation of onboardee is done");
		 return message; 
	  }
	  public String delete(String email) throws Exception{
		  logger.info("Deleting the onboardee");
		  int res;
		  String message = "";
		  try {
			  String sql ="DELETE FROM onboardee_details where email = ?";
			  res =jdbcTemplate.update(sql,email);   // To check deletion has updated in the table
		  }
		  catch(Exception e)
		  {
			  logger.error("Failed at Deletion of Onboardee "+e.toString());
			  message = "Deletion Failed...";
			  return message;
		  }
		  if(res != 0)
		  {
			  message ="Deletion successfully done";
		  }
		  else
		  {
			  message ="No records found.. So No deletion done";
		  }
		  return message;
		  
	  }
//	  public String update(String attribute ,)
	  public List<Object> listAllByAttributes(String attribute) throws Exception{
		  logger.info("Listing the attribute values based on the attributes");
		  List <Object> resultList = new LinkedList<>();
		  try {
			  String sql ="SELECT DISTINCT "+attribute+" FROM onboardee_details";
			  jdbcTemplate.query(sql,
					  (rs,rownum)-> resultList.add((String)rs.getString(1))
					  );
			  
			  
		  }
		  catch(Exception e)
		  {
			  logger.error("Listing Failed ");
			  
		  }
		  return resultList;
	  }

}
