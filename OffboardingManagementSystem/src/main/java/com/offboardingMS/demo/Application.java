package com.offboardingMS.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.offboardingMS.demo.model.SuperAdmin;
import com.offboardingMS.demo.repository.SuperAdminRepo;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Application {
	
	@Autowired
	private SuperAdminRepo superAdminRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
    @PostConstruct
    public void init() {
    	saveSuperUSer();
    }
	
	private void saveSuperUSer(){
		SuperAdmin superadmin = new SuperAdmin("CEO");
		superAdminRepo.save(superadmin);
	}

}
