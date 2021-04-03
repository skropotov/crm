package com.skropotov.crm.controllers;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skropotov.crm.forms.LoginForm;
import com.skropotov.crm.models.Token;
import com.skropotov.crm.repositories.TokenRepository;
import com.skropotov.crm.services.LoginService;
import com.skropotov.crm.transfers.TokenDto;
import com.skropotov.crm.transfers.UserDto;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private TokenRepository tokenRepository;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody  LoginForm loginForm) {
        return ResponseEntity.ok(loginService.login(loginForm));
    }
    
    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(HttpServletRequest request) {
    	UserDto dto = new UserDto();
    	String tokenValue = request.getHeader("token");
    	if (tokenValue != null && tokenValue.length() > 0) {
    		Token token = tokenRepository.findOneByValue(tokenValue).orElseThrow(() -> new EntityNotFoundException("Token not found"));
    		dto = UserDto.from(token.getUser());
    	}
    	return ResponseEntity.ok(dto);
    }
}
