package com.offboardingMS.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offboardingMS.demo.exception.EmployeeNotFoundException;
import com.offboardingMS.demo.exception.NotAllowedException;
import com.offboardingMS.demo.exception.ResignationNotFoundException;
import com.offboardingMS.demo.model.ApplicationUpdateByHrDTO;
import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.HumanResourceManager;
import com.offboardingMS.demo.model.ResignApplication;
import com.offboardingMS.demo.model.ResignationApplicationResponseDTO;
import com.offboardingMS.demo.service.HrService;
import com.offboardingMS.demo.service.HrServiceImple;

@RestController
@RequestMapping(value = "/api/hr/")
public class HrController {
	
	@Autowired
	private HrService hrService; 
	
	@PostMapping("/createApplication/{empId}")
	public ResponseEntity<ResignApplication> initiateOffBoardingProcess(@PathVariable Integer empId) throws EmployeeNotFoundException{
		ResignApplication resignationApp = hrService.initiateOffboardingProcesss(empId);
		return new ResponseEntity<ResignApplication>(resignationApp,HttpStatus.CREATED);
	}
	
	@PostMapping("/checkApplicationStatus/{empId}")
	public ResponseEntity<ResignApplication> checkAppStatus(@PathVariable Integer empId) throws EmployeeNotFoundException{
		ResignApplication resignationApp = hrService.checkStatusOfResignApplication(empId);
		return new ResponseEntity<ResignApplication>(resignationApp,HttpStatus.CREATED);
	}
	
	@PostMapping("/updateApplicationStatus/")
	public ResponseEntity<ResignationApplicationResponseDTO> updateStatusOfApplication(@RequestBody ApplicationUpdateByHrDTO applicationUpdateByHrDTO) throws ResignationNotFoundException, NotAllowedException{
		ResignationApplicationResponseDTO response = hrService.updateStatusOfApplication(applicationUpdateByHrDTO);
		return new ResponseEntity<ResignationApplicationResponseDTO>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteEmployee/")
	public ResponseEntity<String> removeEmployee(@PathVariable Integer empId) throws ResignationNotFoundException, EmployeeNotFoundException{
		String deleteEmp = hrService.removeEmployee(empId);
		return new ResponseEntity<String>(deleteEmp,HttpStatus.OK);
	}
	
	@GetMapping("/myDetails/")
	public ResponseEntity<HumanResourceManager> getMyData(){
		HumanResourceManager mydata = hrService.myDetails();
		return new ResponseEntity<HumanResourceManager>(mydata,HttpStatus.OK);
	}
	

}
