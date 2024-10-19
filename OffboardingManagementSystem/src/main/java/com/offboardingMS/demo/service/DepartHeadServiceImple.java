package com.offboardingMS.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offboardingMS.demo.exception.EmployeeNotFoundException;
import com.offboardingMS.demo.exception.NotAllowedException;
import com.offboardingMS.demo.exception.ResignationNotFoundException;
import com.offboardingMS.demo.model.ApplicationUpdateByDhDto;
import com.offboardingMS.demo.model.DepartmentHead;
import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.HumanResourceManager;
import com.offboardingMS.demo.model.ResignApplication;
import com.offboardingMS.demo.model.ResignationApplicationResponseDTO;
import com.offboardingMS.demo.model.Status;
import com.offboardingMS.demo.repository.DepartmentHeadRepo;
import com.offboardingMS.demo.repository.EmployeeRepo;
import com.offboardingMS.demo.repository.ResignationAppRepo;

@Service
public class DepartHeadServiceImple implements DepartHeadService{
	
	@Autowired
	private ResignationAppRepo resignationAppRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private DepartmentHeadRepo deptHeadRepo;
	

	@Override
	public ResignApplication checkStatusOfResignApplication(Integer appId) throws EmployeeNotFoundException {
		Optional<ResignApplication> resigApp =  resignationAppRepo.findById(appId);
		if(resigApp.isPresent()) {
			return resigApp.get();
		}
		throw new EmployeeNotFoundException("Employee not found by employee Id");
	}

	@Override
	public ResignationApplicationResponseDTO updateStatusOfApplication(ApplicationUpdateByDhDto applicationUpdateByDh) throws ResignationNotFoundException, NotAllowedException, EmployeeNotFoundException {
		Optional<ResignApplication> optionalApp = resignationAppRepo.findById(applicationUpdateByDh.getApplicationId());
		if(optionalApp.isEmpty()) {
			throw new ResignationNotFoundException("Resignation not found by applictionId");
		}
		ResignApplication resigApplication = optionalApp.get();
		System.out.println(resigApplication.getNextStep()+" "+deptHeadRepo.findById(applicationUpdateByDh.getApplicationId()).isPresent());
		if(resigApplication.getNextStep().equals(Status.SCHEDULE_EXIT_INTERVIEW) &&  deptHeadRepo.findById(applicationUpdateByDh.getDeptHeadId()).isPresent()) {
			throw new NotAllowedException("Scheduling interview can only be approved by HR manager");
		}
		else if(resigApplication.getNextStep().equals(Status.KNOWLEDGE_TRANSFER) && deptHeadRepo.findById(applicationUpdateByDh.getDeptHeadId()).isPresent()) {
			ResignationApplicationResponseDTO responseDTO = new ResignationApplicationResponseDTO("Knowledge Transfer completed by employee",resigApplication);
			resigApplication.getHistory().add(Status.KNOWLEDGE_TRANSFER);
			resigApplication.setNextStep(Status.REVOKE_COMPANY_ACCESS);
			resignationAppRepo.save(resigApplication);
			return responseDTO;
		}
		else if(resigApplication.getNextStep().equals(Status.REVOKE_COMPANY_ACCESS) && deptHeadRepo.findById(applicationUpdateByDh.getDeptHeadId()).isPresent()) {
			throw new NotAllowedException("Revoking access can only be approved by HR manager");
		}
		else if(resigApplication.getNextStep().equals(Status.UPDATE_HR_RECORDS) &&  deptHeadRepo.findById(applicationUpdateByDh.getDeptHeadId()).isPresent()) {
			throw new NotAllowedException("Updating HR records can only be approved by HR manager");
		}
		else if(resigApplication.getNextStep().equals(Status.RETRIEVE_COMPANY_PROPERTY) &&  deptHeadRepo.findById(applicationUpdateByDh.getDeptHeadId()).isPresent()) {
			throw new NotAllowedException("Retriving company can only be approved by HR manager");

		}
		else if(resigApplication.getNextStep().equals(Status.CONDUCT_EXIT_INTERVIEW) &&  deptHeadRepo.findById(applicationUpdateByDh.getDeptHeadId()).isPresent()) {
			throw new NotAllowedException("Conducting exit interview can only be approved by HR manager");

		}
		else if(resigApplication.getNextStep().equals(Status.NOTIFIY_ALL_EMPLOYEE) &&  deptHeadRepo.findById(applicationUpdateByDh.getDeptHeadId()).isPresent()) {
			throw new NotAllowedException("Notifying all employee can only be approved by HR manager");
		}
		throw new EmployeeNotFoundException("Applications is closed and Employee is deleted from the system");
	}



}
