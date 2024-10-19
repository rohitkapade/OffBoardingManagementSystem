package com.offboardingMS.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
    @JsonIgnore
    @JsonProperty
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ResignApplication> resignationApplications = new ArrayList<>();
	
	@ManyToOne
	private Department department;
	
    @JsonIgnore
    @JsonProperty
    @ElementCollection
    @CollectionTable
    @Column(name = "notifications", length = 500)
	private List<String> notifications = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ResignApplication> getResignationApplications() {
		return resignationApplications;
	}

	public void setResignationApplications(List<ResignApplication> resignationApplications) {
		this.resignationApplications = resignationApplications;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}
	
	public Employee(String name,Department dept){
		this.name = name;
		this.department = dept;
	}
	
	Employee(){

	}

}
