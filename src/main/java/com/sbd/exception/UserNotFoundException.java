package com.sbd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author rushikeshM
 *	This Is Custom Exception
 *	By Using "@ResponseStatus" We Can Bind Our Exception Class To Revert "404 Not Found" Status To API Caller
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException  {
	
	public UserNotFoundException(String exception) {
		super(exception);
	}
}
