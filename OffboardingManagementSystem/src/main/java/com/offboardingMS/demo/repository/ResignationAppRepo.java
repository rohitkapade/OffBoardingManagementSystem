package com.offboardingMS.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.ResignApplication;


@Repository
public interface ResignationAppRepo extends JpaRepository<ResignApplication, Integer>{

	ResignApplication findByEmployee(Employee emp);

}
