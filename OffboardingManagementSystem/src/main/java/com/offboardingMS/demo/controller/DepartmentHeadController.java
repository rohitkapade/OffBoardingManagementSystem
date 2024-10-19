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
import com.offboardingMS.demo.exception.NotAllowedException;
import com.offboardingMS.demo.exception.ResignationNotFoundException;
import com.offboardingMS.demo.model.ApplicationUpdateByDhDto;
import com.offboardingMS.demo.model.ApplicationUpdateByHrDTO;
import com.offboardingMS.demo.model.ResignApplication;
import com.offboardingMS.demo.model.ResignationApplicationResponseDTO;
import com.offboardingMS.demo.service.DepartHeadService;

@RestController
@RequestMapping(value = "/api/deptHead/")
public class DepartmentHeadController {

	@Autowired
	private DepartHeadService departHeadService;
	
	@PostMapping("/checkApplicationStatus/{empId}")
	public ResponseEntity<ResignApplication> checkAppStatus(@PathVariable Integer empId) throws EmployeeNotFoundException{
		ResignApplication resignationApp = departHeadService.checkStatusOfResignApplication(empId);
		return new ResponseEntity<ResignApplication>(resignationApp,HttpStatus.CREATED);
	}
	
	@PostMapping("/updateApplicationStatus/")
	public ResponseEntity<ResignationApplicationResponseDTO> updateStatusOfApplication(@RequestBody ApplicationUpdateByDhDto applicationUpdateByDhDto) throws ResignationNotFoundException, NotAllowedException{
		System.out.println("in dh contro");
		ResignationApplicationResponseDTO response = departHeadService.updateStatusOfApplication(applicationUpdateByDhDto);
		return new ResponseEntity<ResignationApplicationResponseDTO>(response,HttpStatus.OK);
	}
}
