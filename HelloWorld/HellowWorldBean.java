package com.aci.payon.rest.aprosewebservices.restfulwebservices.HelloWorld;

public class HellowWorldBean {

	String message;
	
	public HellowWorldBean(String msg) {
		this.message =  msg;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
