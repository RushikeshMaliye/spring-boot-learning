package com.sbd.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author rushikeshM
 *	This Is Custom Exception
 *	By Using "@ResponseStatus" We Can Bind Our Exception Class To Revert "406 Not Acceptable" Status To API Caller
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class EmptyRequestBeanException extends RuntimeException  {
	
	public EmptyRequestBeanException(String exception) {
		super(exception);
	}
}
