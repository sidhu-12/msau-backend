package com.accolite.msaumanagement.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler {

	// 404 Exception Handler
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Map<String, String>> handle(NoHandlerFoundException e) {

		Map<String, String> map = new HashMap<>();
		map.put("responseMessage",
				"This is an internal server problem. SORRY FOR THE INCONVENIENCE" + e.getLocalizedMessage());
		HttpStatus status = HttpStatus.NOT_FOUND;
		return new ResponseEntity(map, status);
	}

	// Global Exception Handler
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Map<String, String>> exceptionhandler(Exception e) {
		Map<String, String> map = new HashMap<>();
		map.put("responseMessage",
				"Something went wrong.Sorry for the inconvenience caused!" + e.getLocalizedMessage());
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ResponseEntity(map, status);
	}

}
