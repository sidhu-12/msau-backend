package com.accolite.msaumanagement.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.apache.bcel.generic.IINC;
import org.hibernate.JDBCException;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.accolite.msaumanagement.exception.CustomExceptionHandler;
import com.accolite.msaumanagement.model.Onboardee;

import net.bytebuddy.asm.Advice.Return;

@Repository
public class OnboardeeDAO {
	 private static final Logger logger = LogManager.getLogger(OnboardeeDAO.class);
	 
	  @Autowired
	  JdbcTemplate jdbcTemplate;
	  
	  public String create(Onboardee newOnboardee) throws Exception{
		  logger.info("Creating the onboardee for " + newOnboardee.getName());
		  String message ="";
		  try {
			  String sql = "INSERT INTO onboardee_details(email,name,phoneNo,demandId,location,skills,startDate,backgroundCheckStatus,onboardStatus)"
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
		  logger.info("Deleting the onboardee for " +email);
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
			  throw new CustomExceptionHandler("Deletion failed because "+e.getMessage());
		  }
		  if(res != 0)
		  {
			  message ="Deletion successfully done";
		  }
		  else
		  {
				 logger.info(" No records found.. So No deletion has been done");
				 message ="No records found.. So No deletion done";
		  }
		  return message;
		  
	  }
	  public String update(List<String> attributes ,List<String> values, String email  )
	  {
		  logger.info("Updating the attribute named "+attributes+" for "+email);
		  String messageString = "";
		  int res = -1;
		  try {
			  
			StringBuilder sqlString =  new StringBuilder("UPDATE onboardee_details SET ");
			for (int i =0 ;i < attributes.size() ;i++)
			{
				sqlString.append(attributes.get(i)+"='"+ values.get(i)+"', ");
			}
			sqlString.delete(sqlString.length()-2,sqlString.length()-1);
			sqlString.append(" where email = ?"); 
			res = jdbcTemplate.update(sqlString.toString(),email);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(" Updation Failed due to "+e.toString());
			throw new CustomExceptionHandler("Updation Failed due to "+e.getMessage());
			
			
		}
		  if(res == 0) //To check whether updation has been done properly
		  {
			 logger.info(" Updation has not been done because onboardee is not found");
			  messageString = "Updation has not been done because onboardee is not found";
		  }
		  else {
			messageString ="Updation successfully done";
		}
		  return messageString;
	  }
	  public List<Object> listAllByAttributes(String attribute) throws Exception{
		  logger.info("Listing the attribute values based on the "+attribute);
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
	  
	  public List<Onboardee> listAll(String attribute , String value) throws Exception{
		  
		  logger.info("Listing the Onboardee details based on the " +attribute);
		  List <Onboardee> listofOnboardees = new LinkedList<>();
		  try {
			  String sql = "SELECT * from onboardee_details WHERE "+attribute+" = ?";
			  jdbcTemplate.query(sql,new String[] {value},
					  (rs,rownum)-> new Onboardee(
							  rs.getString("email"),
							  rs.getString("name"),
							  rs.getLong("phoneNo"),
							  rs.getInt("demandId"),
							  rs.getString("location"),
							  rs.getString("skills"),
							  rs.getDate("startDate"),
							  rs.getString("backgroundCheckStatus"),
							  rs.getString("onboardStatus"))).forEach(onboardee->{
								  listofOnboardees.add(onboardee);
							  });
		  }
		  catch (Exception e) {
			// TODO: handle exception
			  logger.error("List of onboardee failed : "+e.getLocalizedMessage());
			  throw new CustomExceptionHandler("Onboardee List Failed");
		}
		  return listofOnboardees;
	  }
			
}
