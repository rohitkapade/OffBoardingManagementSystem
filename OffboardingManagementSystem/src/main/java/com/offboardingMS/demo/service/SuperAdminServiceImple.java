package com.offboardingMS.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.offboardingMS.demo.exception.EmployeeNotFoundException;
import com.offboardingMS.demo.model.AddDepartmentDTO;
import com.offboardingMS.demo.model.Department;
import com.offboardingMS.demo.model.DepartmentHead;
import com.offboardingMS.demo.model.DepartmentHeadDTO;
import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.HumanResourceManager;
import com.offboardingMS.demo.model.ResignApplication;
import com.offboardingMS.demo.model.SuperAdmin;
import com.offboardingMS.demo.repository.DepartmentHeadRepo;
import com.offboardingMS.demo.repository.DepartmentRepo;
import com.offboardingMS.demo.repository.EmployeeRepo;
import com.offboardingMS.demo.repository.HumanResourceManagerRepo;
import com.offboardingMS.demo.repository.ResignationAppRepo;
import com.offboardingMS.demo.repository.SuperAdminRepo;

@Service
public class SuperAdminServiceImple implements SuperAdminService{
	
	@Autowired
	private HumanResourceManagerRepo hrRepo;
	
	@Autowired	
	private DepartmentRepo deptRepo;
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@Autowired
	private DepartmentHeadRepo deptHead;
	
	@Autowired
	private ResignationAppRepo resignationAppRepo;
	


	@Override
	public String addHR(HumanResourceManager hr) {
		hrRepo.save(hr);
		return "HR saved";
	}
	


	@Override
	public String addDepartment(Department dept) {
		deptRepo.save(dept);
		return "Department saved";
	}

	@Override
	public String addEmployee(AddDepartmentDTO addDepartmentDTO) {
		Employee employee = new Employee(addDepartmentDTO.getName(),deptRepo.findById(addDepartmentDTO.getDepartmentId()).get());
		empRepo.save(employee);
		return "Employee saved";
	}

	@Override
	public String addDeptHead(AddDepartmentDTO departHead) {
		DepartmentHead deptHead1 = new DepartmentHead();
		deptHead1.setName(departHead.getName());
		deptHead.save(deptHead1);
		return "Department Head saved";
	}

	@Override
	public ResignApplication checkStatusOfResignApplication(Integer empId) throws EmployeeNotFoundException {
		Optional<Employee> employee = empRepo.findById(empId);
		if(employee.isPresent()) {
			Employee emp = employee.get();
			List<ResignApplication> app = resignationAppRepo.findByEmployee(emp);
			return app.get(0);
		}
		throw new EmployeeNotFoundException("Employee not found by employee Id");
	}

	@Override
	public String setDeptHeadToDept(DepartmentHeadDTO deptHeadDTO) {
		Department dept = deptRepo.findById(deptHeadDTO.getDeptId()).get();
		dept.setDeparthead(deptHead.findById(deptHeadDTO.getDeptHeadId()).get());
		deptRepo.save(dept);
		return "Department head set to department";
	}

}
