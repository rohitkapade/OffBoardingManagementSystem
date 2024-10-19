package com.offboardingMS.demo.service;

import org.springframework.stereotype.Service;

import com.offboardingMS.demo.exception.EmployeeNotFoundException;
import com.offboardingMS.demo.model.AddDepartmentDTO;
import com.offboardingMS.demo.model.Department;
import com.offboardingMS.demo.model.DepartmentHead;
import com.offboardingMS.demo.model.DepartmentHeadDTO;
import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.HumanResourceManager;
import com.offboardingMS.demo.model.ResignApplication;

@Service
public interface SuperAdminService {
	
	public String addHR(HumanResourceManager hr);
	public String addDepartment(Department dept);
	public String addEmployee(AddDepartmentDTO addDeptDTO);
	public String addDeptHead(AddDepartmentDTO departHead);
	public ResignApplication checkStatusOfResignApplication(Integer empId) throws EmployeeNotFoundException;
	public String setDeptHeadToDept(DepartmentHeadDTO deptHeadDTO);

}
