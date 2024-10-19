package com.offboardingMS.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class DepartmentHead extends Employee{
	
	public DepartmentHead(){
		super();
	}

	public DepartmentHead(String name, Department dept) {
		super(name, dept);
		// TODO Auto-generated constructor stub
	}

    @JsonIgnore
    @JsonProperty
	@ManyToMany(cascade = CascadeType.ALL)
	private List<ResignApplication> applications = new ArrayList<>();

    
	public List<ResignApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<ResignApplication> applications) {
		this.applications = applications;
	}
	
	
	
	
}
