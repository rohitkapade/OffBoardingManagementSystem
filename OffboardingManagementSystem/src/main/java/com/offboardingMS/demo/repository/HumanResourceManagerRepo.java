package com.offboardingMS.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.offboardingMS.demo.model.HumanResourceManager;


@Repository
public interface HumanResourceManagerRepo extends JpaRepository<HumanResourceManager, Integer>{

}
