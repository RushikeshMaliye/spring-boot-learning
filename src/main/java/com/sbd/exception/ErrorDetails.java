package com.sbd.exception;

/**
 * @author rushikeshM
 *	This Class is for Error Details, simply
 *	Defines In What Format Our Project Should Show Exception-Error Message To API Caller.
 */
public class ErrorDetails {
	
	private String generatedBy;
	private String message;
	private String details;

	public ErrorDetails(String generatedBy, String message, String details) {
	    super();
	    this.generatedBy= generatedBy;
	    this.message = message;
	    this.details = details;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
  
	public String getGeneratedBy() {
		return generatedBy;
	}
	
}
