package com.skropotov.crm.security.token;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.skropotov.crm.models.User;

import java.util.Collection;

public class TokenAuthentication implements Authentication {

	private static final long serialVersionUID = -8244480122187286590L;
	private String token;
    private boolean isAuthenticated;
    private User user;


    public TokenAuthentication(String token) {
        this.token = token;
    }

    public void setUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }

    @Override
    public Object getDetails() {
        return user;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated  = isAuthenticated;
    }

    @Override
    public String getName() {
        return token;
    }
}
