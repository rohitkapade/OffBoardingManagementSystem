package com.offboardingMS.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String deptName;
	
	@OneToMany(cascade = CascadeType.ALL)
	@Column(nullable = true)
	private List<Employee> employee = new ArrayList();
	
	@OneToOne
	private DepartmentHead departhead;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public DepartmentHead getDeparthead() {
		return departhead;
	}
	public void setDeparthead(DepartmentHead departhead) {
		this.departhead = departhead;
	}	
	
	
}
