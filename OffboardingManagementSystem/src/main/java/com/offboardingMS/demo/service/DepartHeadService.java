package com.offboardingMS.demo.service;

import org.springframework.stereotype.Service;

import com.offboardingMS.demo.exception.EmployeeNotFoundException;
import com.offboardingMS.demo.exception.NotAllowedException;
import com.offboardingMS.demo.exception.ResignationNotFoundException;
import com.offboardingMS.demo.model.ApplicationUpdateByDhDto;
import com.offboardingMS.demo.model.ResignApplication;
import com.offboardingMS.demo.model.ResignationApplicationResponseDTO;


@Service
public interface DepartHeadService {
	
	public ResignApplication checkStatusOfResignApplication(Integer empId) throws EmployeeNotFoundException;
//	public ResignApplication updateStatusOfApplication(Integer appId);
	public ResignationApplicationResponseDTO updateStatusOfApplication(ApplicationUpdateByDhDto applicationUpdateByDh) throws ResignationNotFoundException, NotAllowedException, EmployeeNotFoundException;
}
