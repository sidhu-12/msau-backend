package com.accolite.msaumanagement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@SpringBootApplication
public class MSAUManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MSAUManagementApplication.class, args);
	}

}
