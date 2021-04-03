package com.skropotov.crm.transfers;

import com.skropotov.crm.models.Token;

public class TokenDto {
    private String value;

    public TokenDto(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static TokenDto from(Token token) {
        return new TokenDto(token.getValue());
    }

} 