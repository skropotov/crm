package com.skropotov.crm.security.providers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.skropotov.crm.models.Token;
import com.skropotov.crm.repositories.TokenRepository;
import com.skropotov.crm.security.token.TokenAuthentication;


@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private TokenRepository tokenRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		TokenAuthentication tokenAuthentication = (TokenAuthentication)authentication;
		Optional<Token> tokenCandidate = tokenRepository.findOneByValue(tokenAuthentication.getName());
		
		if (tokenCandidate.isPresent()) {
			tokenAuthentication.setUserDetails(tokenCandidate.get().getUser());
			tokenAuthentication.setAuthenticated(true);
			return tokenAuthentication;
		}
		else 
			throw new IllegalArgumentException("Bad token");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return TokenAuthentication.class.equals(authentication);
	} 
}
