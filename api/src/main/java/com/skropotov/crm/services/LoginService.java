package com.skropotov.crm.services;

import com.skropotov.crm.forms.LoginForm;
import com.skropotov.crm.transfers.TokenDto;

public interface LoginService {
    TokenDto login(LoginForm loginForm);
}
