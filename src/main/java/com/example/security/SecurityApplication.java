package com.example.security;

import com.example.security.configuration.AppUser;
import com.example.security.configuration.LoggedInUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}
	@GetMapping("/secured")
	public Object secured(@LoggedInUser AppUser appUser){
		return appUser;

	}
	@GetMapping("/secured-admin")
	@PreAuthorize("hasRole('ROLE_admin')")
	public String securedAdmin(){
		return "Only admin can see this";
	}
	@GetMapping("/public")
	public String pub(){
		return "this is public";
	}
}
