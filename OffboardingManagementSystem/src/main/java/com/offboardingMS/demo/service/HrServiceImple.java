package com.offboardingMS.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offboardingMS.demo.exception.EmployeeNotFoundException;
import com.offboardingMS.demo.exception.NotAllowedException;
import com.offboardingMS.demo.exception.ResignationNotFoundException;
import com.offboardingMS.demo.model.ApplicationUpdateByHrDTO;
import com.offboardingMS.demo.model.DepartmentHead;
import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.HumanResourceManager;
import com.offboardingMS.demo.model.ResignApplication;
import com.offboardingMS.demo.model.ResignationApplicationResponseDTO;
import com.offboardingMS.demo.model.Status;
import com.offboardingMS.demo.model.SuperAdmin;
import com.offboardingMS.demo.repository.DepartmentHeadRepo;
import com.offboardingMS.demo.repository.EmployeeRepo;
import com.offboardingMS.demo.repository.HumanResourceManagerRepo;
import com.offboardingMS.demo.repository.ResignationAppRepo;
import com.offboardingMS.demo.repository.SuperAdminRepo;


@Service
public class HrServiceImple implements HrService{
	
	@Autowired
	private DepartmentHeadRepo allDepartmentHeads;
	
	@Autowired
	private ResignationAppRepo resignationRepo;
	
	@Autowired
	private HumanResourceManagerRepo hrRepo;
	
	@Autowired
	private SuperAdminRepo superAdminRepo;
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private ResignationAppRepo resignRepo;

	@Override
	public String offboardingProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResignApplication initiateOffboardingProcesss(Integer empId) throws EmployeeNotFoundException {
		
		Optional<Employee> employee1 = employeeRepo.findById(empId);
		if(employee1.isEmpty()) {
			throw new EmployeeNotFoundException("Employee is not present");
		}
		Employee employee = employee1.get();
		ResignApplication employeeResignation = new ResignApplication(employee);
		//Notify management
		List<DepartmentHead> departmentHead = allDepartmentHeads.findAll();
		for(DepartmentHead departmentHeademp:departmentHead) {
			departmentHeademp.getNotifications().add("Employee"+" "+employee.getName()+" from department "+employee.getDepartment().getDeptName()+" has submitted Resignation");
			departmentHeademp.getApplications().add(employeeResignation);
			allDepartmentHeads.save(departmentHeademp);
		}
		employeeResignation.setNextStep(Status.NOTIFY_MANAGEMENT);
		resignationRepo.save(employeeResignation);

		HumanResourceManager hr = hrRepo.findAll().get(0);
		hr.getApplications().add(employeeResignation);
		hr.getNotifications().add("Employee"+" "+employee.getName()+" from department "+employee.getDepartment().getDeptName()+" has submitted Resignation");
		hrRepo.save(hrRepo.findAll().get(0));


		
		SuperAdmin superAdmin = superAdminRepo.findAll().get(0);
		superAdmin.getApplications().add(employeeResignation);
		superAdmin.getNotifications().add("Employee"+" "+employee.getName()+" from department "+employee.getDepartment().getDeptName()+" has submitted Resignation");
		superAdminRepo.save(superAdminRepo.findAll().get(0));
		employeeResignation.getHistory().add(Status.NOTIFY_MANAGEMENT);

		employeeResignation.setNextStep(Status.SCHEDULE_EXIT_INTERVIEW);

		resignRepo.save(employeeResignation);
		

		return employeeResignation;
	}

	@Override
	public ResignApplication checkStatusOfResignApplication(Integer appId) throws EmployeeNotFoundException {
		Optional<ResignApplication> resigApp =  resignRepo.findById(appId);
		if(resigApp.isPresent()) {
			return resigApp.get();
		}
		throw new EmployeeNotFoundException("Employee not found by employee Id");
	}

	@Override
	public ResignationApplicationResponseDTO updateStatusOfApplication(ApplicationUpdateByHrDTO applicationUpdateByHr) throws ResignationNotFoundException, NotAllowedException, EmployeeNotFoundException {
		Optional<ResignApplication> optionalApp = resignationRepo.findById(applicationUpdateByHr.getApplicationId());
		if(optionalApp.isEmpty()) {
			throw new ResignationNotFoundException("Resignation not found by applictionId");
		}

		ResignApplication resigApplication = optionalApp.get();
		if(resigApplication.getNextStep().equals(Status.SCHEDULE_EXIT_INTERVIEW) && hrRepo.findById(applicationUpdateByHr.getHrManagerId()).isPresent()) {
			ResignationApplicationResponseDTO responseDTO = new ResignationApplicationResponseDTO("Interview is successfuly scheduled.",resigApplication);
			resigApplication.getHistory().add(Status.SCHEDULE_EXIT_INTERVIEW);
			resigApplication.setNextStep(Status.KNOWLEDGE_TRANSFER);
			resignationRepo.save(resigApplication);
			return responseDTO;
		}
		else if(resigApplication.getNextStep().equals(Status.KNOWLEDGE_TRANSFER) && hrRepo.findById(applicationUpdateByHr.getHrManagerId()).isPresent()) {
			throw new NotAllowedException("Next Step is Knowledge Transfer, which can only be approved by  DepartmentHead");
		}
		else if(resigApplication.getNextStep().equals(Status.REVOKE_COMPANY_ACCESS) && hrRepo.findById(applicationUpdateByHr.getHrManagerId()).isPresent()) {
			ResignationApplicationResponseDTO responseDTO = new ResignationApplicationResponseDTO("Company Access revoked",resigApplication);
			resigApplication.getHistory().add(Status.REVOKE_COMPANY_ACCESS);
			resigApplication.setNextStep(Status.UPDATE_HR_RECORDS);
			resignationRepo.save(resigApplication);
			return responseDTO;
		}
		else if(resigApplication.getNextStep().equals(Status.RETRIEVE_COMPANY_PROPERTY) && hrRepo.findById(applicationUpdateByHr.getHrManagerId()).isPresent()) {
			ResignationApplicationResponseDTO responseDTO = new ResignationApplicationResponseDTO("Company Property Retrived",resigApplication);
			resigApplication.getHistory().add(Status.RETRIEVE_COMPANY_PROPERTY);
			resigApplication.setNextStep(Status.CONDUCT_EXIT_INTERVIEW);
			resignationRepo.save(resigApplication);
			return responseDTO;
		}
		else if(resigApplication.getNextStep().equals(Status.CONDUCT_EXIT_INTERVIEW) && hrRepo.findById(applicationUpdateByHr.getHrManagerId()).isPresent()) {
			ResignationApplicationResponseDTO responseDTO = new ResignationApplicationResponseDTO("Exit interview Completed",resigApplication);
			resigApplication.getHistory().add(Status.CONDUCT_EXIT_INTERVIEW);
			resigApplication.setNextStep(Status.NOTIFIY_ALL_EMPLOYEE);
			resignationRepo.save(resigApplication);
			return responseDTO;
		}
		else if(resigApplication.getNextStep().equals(Status.NOTIFIY_ALL_EMPLOYEE) && hrRepo.findById(applicationUpdateByHr.getHrManagerId()).isPresent()) {
			ResignationApplicationResponseDTO responseDTO = new ResignationApplicationResponseDTO("Notified to All Employees.",resigApplication);
			resigApplication.getHistory().add(Status.NOTIFIY_ALL_EMPLOYEE);
			resigApplication.setNextStep(Status.PROCESS_COMPLETED_THANKS);
			resignationRepo.save(resigApplication);
			return responseDTO;
		}
		else if(resigApplication.getNextStep().equals(Status.UPDATE_HR_RECORDS) && hrRepo.findById(applicationUpdateByHr.getHrManagerId()).isPresent()) {
			ResignationApplicationResponseDTO responseDTO = new ResignationApplicationResponseDTO("HR records Updated",resigApplication);
			Optional<ResignApplication> resigApp =  resignRepo.findById(applicationUpdateByHr.getApplicationId());
			if(resigApp.isEmpty()) {
				throw new ResignationNotFoundException("No resignation found by application id");
			}
			employeeRepo.delete(resigApp.get().getEmployee());
			resigApplication.getHistory().add(Status.UPDATE_HR_RECORDS);
			resigApplication.setNextStep(Status.RETRIEVE_COMPANY_PROPERTY);
			resignationRepo.save(resigApplication);
			return responseDTO;
		}
		throw new EmployeeNotFoundException("Applications is closed and Employee is deleted from the system");
	}

	@Override
	public String removeEmployee(Integer empid) throws ResignationNotFoundException, EmployeeNotFoundException {
		Optional<Employee> employee = employeeRepo.findById(empid);
		if(employee.isPresent()) {
			Employee emp = employee.get();
			try {
				List<ResignApplication> application= resignationRepo.findByEmployee(emp);
				if(application.get(0).getHistory().get(application.get(0).getHistory().size()-1).equals(Status.NOTIFIY_ALL_EMPLOYEE)) {
					employeeRepo.delete(emp);
				}
			}
			catch(Exception ex) {
				throw new ResignationNotFoundException("Employee hasn't applied for resignation or Offboarding process is incomplete");
			}
			
		}
		throw new EmployeeNotFoundException("Employee not found by employee Id");
	}

	@Override
	public HumanResourceManager myDetails() {
		HumanResourceManager hr =  hrRepo.findAll().get(0);
		return hr;
	}

}
