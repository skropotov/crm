package com.skropotov.crm.services;


import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.skropotov.crm.forms.LoginForm;
import com.skropotov.crm.models.Status;
import com.skropotov.crm.models.Token;
import com.skropotov.crm.models.User;
import com.skropotov.crm.repositories.TokenRepository;
import com.skropotov.crm.repositories.UserRepository;
import com.skropotov.crm.transfers.TokenDto;
import static com.skropotov.crm.transfers.TokenDto.from;

@Component
public class LoginServiceImpl implements LoginService {
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
	@Override
	public TokenDto login(LoginForm loginForm) {
        User user = userRepository.findByUsername(loginForm.getLogin()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        
        if (user.getStatus() != Status.ACTIVE) {
        	throw new IllegalArgumentException("User isn't active");
        }
        
        if (!passwordEncoder.matches(loginForm.getPassword(), user.getPassword())) {
        	throw new IllegalArgumentException("Invalid password");
        }
        
    	Token token = new Token();
    	token.setValue(RandomStringUtils.random(10, true, true));
    	token.setUser(user);

    	tokenRepository.save(token);
    	return from(token);
	}

	@Override
	public User currentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return ((User)authentication.getPrincipal());
	}


}
