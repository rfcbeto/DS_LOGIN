package br.com.digital.sorte.login.data.dto.security;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AccountCredentialsDTO implements Serializable{

	private static final long serialVersionUID = 8L;
	

	private String username;
	private String password;

	public AccountCredentialsDTO(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
