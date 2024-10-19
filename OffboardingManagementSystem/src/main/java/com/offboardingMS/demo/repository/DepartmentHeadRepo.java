package com.offboardingMS.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offboardingMS.demo.model.DepartmentHead;

@Repository
public interface DepartmentHeadRepo extends JpaRepository<DepartmentHead, Integer>{

}
