package com.offboardingMS.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offboardingMS.demo.model.SuperAdmin;


@Repository
public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Integer>{

}
