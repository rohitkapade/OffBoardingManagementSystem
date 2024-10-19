package com.offboardingMS.demo.exception;

public class NotAllowedException extends Exception{

	public NotAllowedException(){
		
	}
	
	public NotAllowedException(String str){
		super(str);
	}
}
