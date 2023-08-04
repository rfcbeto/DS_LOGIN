package br.com.digital.sorte.login.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNameNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8L;
	
	public UserNameNotFoundException(String exception) {
		super(exception);
	}
}
