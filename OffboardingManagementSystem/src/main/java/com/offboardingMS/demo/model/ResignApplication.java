package com.offboardingMS.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class ResignApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(nullable = false)
	private Employee employee;
    
    @Enumerated(EnumType.STRING)
    private Status nextStep;
    
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Status> history = new ArrayList();
	
    @ManyToMany(cascade = CascadeType.ALL)
    private List<DepartmentHead> departmentHeadList = new ArrayList<>();
    
    @ManyToOne(cascade = CascadeType.ALL)
    private HumanResourceManager hr;
    
    @ManyToOne(cascade = CascadeType.ALL)
    private SuperAdmin superAdmin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<DepartmentHead> getDepartmentHeadList() {
		return departmentHeadList;
	}

	public void setDepartmentHeadList(List<DepartmentHead> departmentHeadList) {
		this.departmentHeadList = departmentHeadList;
	}

	public HumanResourceManager getHr() {
		return hr;
	}

	public void setHr(HumanResourceManager hr) {
		this.hr = hr;
	}

	public SuperAdmin getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(SuperAdmin superAdmin) {
		this.superAdmin = superAdmin;
	}

	public Status getNextStep() {
		return nextStep;
	}

	public void setNextStep(Status nextStep) {
		this.nextStep = nextStep;
	}

	public List<Status> getHistory() {
		return history;
	}

	public void setHistory(List<Status> history) {
		this.history = history;
	}

	public ResignApplication(Employee employee) {
		super();
		this.employee = employee;
	}
	
	public ResignApplication() {

	}
    
    

}
