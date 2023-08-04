package br.com.digital.sorte.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.sorte.login.data.dto.security.AccountCredentialsDTO;
import br.com.digital.sorte.login.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "/signin")
	public ResponseEntity<?> signin(@RequestBody AccountCredentialsDTO data){
		
		if(chechIfParamsIsNotNull(data)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		}
		var token = authService.signin(data);
		if (token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		}
		
		return token;
	}

	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity<?> refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken){
		
		if(chechIfParamsIsNotNull(username, refreshToken)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		}
		
		var token = authService.refreshToken(username, refreshToken);
		
		if (token == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		}
		
		return token;
	}

	private boolean chechIfParamsIsNotNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank()
				|| username == null || username.isBlank();
	}
	
	private boolean chechIfParamsIsNotNull(AccountCredentialsDTO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank()
				|| data.getPassword() == null || data.getPassword().isBlank();
	}

}
