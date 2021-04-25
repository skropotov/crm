package com.skropotov.crm.security.providers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import com.skropotov.crm.models.User;
import com.skropotov.crm.services.LoginService;

public class AuditorAwareImpl implements AuditorAware<String> {
	@Autowired
	LoginService loginService;
	
	@Override
	public Optional<String> getCurrentAuditor() {
		User user = loginService.currentUser();
		
		if (user == null) {
			return Optional.empty();
		}
		return Optional.ofNullable(user.getUsername());
	}

}
