package com.offboardingMS.demo.service;

import org.springframework.stereotype.Service;

import com.offboardingMS.demo.exception.EmployeeNotFoundException;
import com.offboardingMS.demo.exception.NotAllowedException;
import com.offboardingMS.demo.exception.ResignationNotFoundException;
import com.offboardingMS.demo.model.ApplicationUpdateByHrDTO;
import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.HumanResourceManager;
import com.offboardingMS.demo.model.ResignApplication;
import com.offboardingMS.demo.model.ResignationApplicationResponseDTO;


@Service
public interface HrService {
	
	public String offboardingProcess();
	public ResignApplication initiateOffboardingProcesss(Integer empId) throws EmployeeNotFoundException;
	public ResignApplication checkStatusOfResignApplication(Integer appId) throws EmployeeNotFoundException;
	public String removeEmployee(Integer id) throws ResignationNotFoundException, EmployeeNotFoundException;
	public ResignationApplicationResponseDTO updateStatusOfApplication(ApplicationUpdateByHrDTO applicationUpdateByHr)
			throws ResignationNotFoundException, NotAllowedException, EmployeeNotFoundException;
	public HumanResourceManager myDetails();

}
