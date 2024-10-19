package com.offboardingMS.demo.model;

public class ResignationApplicationResponseDTO {
	
	private String status;
	private ResignApplication resignationApp;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ResignApplication getResignationApp() {
		return resignationApp;
	}
	public void setResignationApp(ResignApplication resignationApp) {
		this.resignationApp = resignationApp;
	}
	public ResignationApplicationResponseDTO(String status, ResignApplication resignationApp) {
		super();
		this.status = status;
		this.resignationApp = resignationApp;
	}
	
	

}
