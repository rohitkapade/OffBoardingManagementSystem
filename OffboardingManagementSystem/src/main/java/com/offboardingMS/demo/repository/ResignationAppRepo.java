package com.offboardingMS.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offboardingMS.demo.model.Employee;
import com.offboardingMS.demo.model.ResignApplication;


@Repository
public interface ResignationAppRepo extends JpaRepository<ResignApplication, Integer>{

	List<ResignApplication> findByEmployee(Employee emp);

}
