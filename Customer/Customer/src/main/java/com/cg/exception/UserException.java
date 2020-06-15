package com.cg.exception;

public class UserException extends Exception {
	
	String msg;
	
	public UserException(String msg) {
		super();
		this.msg=msg;
		
	}

	@Override
	public String toString() {
		return "CustomerException [msg=" + msg + "]";
	}

	
	
}
