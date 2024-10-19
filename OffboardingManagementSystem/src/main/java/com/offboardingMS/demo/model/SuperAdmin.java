package com.offboardingMS.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.offboardingMS.demo.Application;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class SuperAdmin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	
    @JsonIgnore
    @JsonProperty
    @ElementCollection
    @CollectionTable
    @Column(name = "notifications", length = 500)
	private List<String> notifications = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ResignApplication> applications = new ArrayList<>();

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

	public List<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}

	public List<ResignApplication> getApplications() {
		return applications;
	}

	public void setApplications(List<ResignApplication> applications) {
		this.applications = applications;
	}
	
	public SuperAdmin(String name){
		
	}
	
	public SuperAdmin() {
		
	}
	
}	
