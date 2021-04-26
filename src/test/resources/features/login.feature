Feature: Login functionality 
	@Scenario1
	Scenario: Successful Login with Valid entries	
	When the client calls  login
	Then the client receives status code of 200
	And the client  receives response message as Login Successfully
	
	
	
	
	@Scenario2
	Scenario: Unsuccessful Login with invalid email	
	When the client calls  login with invalid email
	Then the client receives status code of 200
	And the client  receives response message as User's email is  not registered yet
	
	
	
	@Scenario3
	Scenario: Unsuccessful Login with invalid password	
	When the client calls  login with invalid password
	Then the client receives status code of 200
	And the client  receives response message as Login Unsuccessful : Password incorrect