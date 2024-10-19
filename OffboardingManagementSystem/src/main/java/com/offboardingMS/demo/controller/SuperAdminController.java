package com.offboardingMS.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offboardingMS.demo.exception.EmployeeNotFoundException;
import com.offboardingMS.demo.model.AddDepartmentDTO;
import com.offboardingMS.demo.model.Department;
import com.offboardingMS.demo.model.DepartmentHead;
import com.offboardingMS.demo.model.DepartmentHeadDTO;
import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.HumanResourceManager;
import com.offboardingMS.demo.model.ResignApplication;
import com.offboardingMS.demo.service.SuperAdminService;

@RestController
@RequestMapping(value = "/api/superAdmin")
public class SuperAdminController {

	@Autowired
	private SuperAdminService superAdminService;
	
	@PostMapping("/addHr/")
	public ResponseEntity<String> addHr(@RequestBody HumanResourceManager hr){
		String addHr = superAdminService.addHR(hr);
		return new ResponseEntity<String>(addHr,HttpStatus.CREATED);
	}
	
	@PostMapping("/addDepartment/")
	public ResponseEntity<String> addDept(@RequestBody Department department){
		String adddepartment = superAdminService.addDepartment(department);
		return new ResponseEntity<String>(adddepartment,HttpStatus.CREATED);
	}
	
	@PostMapping("/addEmployee/")
	public ResponseEntity<String> addEmployees(@RequestBody AddDepartmentDTO addDepartDTO){
		String addEmployee = superAdminService.addEmployee(addDepartDTO);
		return new ResponseEntity<String>(addEmployee,HttpStatus.CREATED);
	}
	
	@PostMapping("/addDeptHead/")
	public ResponseEntity<String> addDeptHead(@RequestBody AddDepartmentDTO deptHead){
		String addDeptHEad = superAdminService.addDeptHead(deptHead);
		return new ResponseEntity<String>(addDeptHEad,HttpStatus.CREATED);
	}
	
	@PostMapping("/checkApplicationStatus/{empId}")
	public ResponseEntity<ResignApplication> checkAppStatus(@PathVariable Integer empId) throws EmployeeNotFoundException{
		ResignApplication resignationApp = superAdminService.checkStatusOfResignApplication(empId);
		return new ResponseEntity<ResignApplication>(resignationApp,HttpStatus.CREATED);
	}
	
	
	@PostMapping("/setHeadOfDept/")
	public ResponseEntity<String> setHeadOfDept(@RequestBody DepartmentHeadDTO deptHeadDTO) throws EmployeeNotFoundException{
		String setDeptHead = superAdminService.setDeptHeadToDept(deptHeadDTO);
		return new ResponseEntity<String>(setDeptHead,HttpStatus.CREATED);
	}
	
}
