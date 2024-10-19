package com.offboardingMS.demo.exception;

public class EmployeeNotFoundException extends Exception{
	
	EmployeeNotFoundException(){
		
	}
	
	public EmployeeNotFoundException(String str){
		super(str);
	}

}
