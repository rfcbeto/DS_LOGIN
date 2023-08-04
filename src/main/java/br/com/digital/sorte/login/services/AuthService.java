package br.com.digital.sorte.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.digital.sorte.login.data.dto.security.AccountCredentialsDTO;
import br.com.digital.sorte.login.data.dto.security.TokenDTO;
import br.com.digital.sorte.login.repositories.UserRepository;
import br.com.digital.sorte.login.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserRepository repository;

	public ResponseEntity<?> signin(AccountCredentialsDTO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			var user = repository.findByUsername(username);

			var tokenResponse = new TokenDTO();
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found!");
			}

			return ResponseEntity.ok(tokenResponse);

		} catch (Exception e) {
			e.printStackTrace();
			throw new BadCredentialsException("Invalid username/password supplied!");
		}

	}

	public ResponseEntity<?> refreshToken(String username, String refreshToken) {

		var user = repository.findByUsername(username);

		var tokenResponse = new TokenDTO();
		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}

		return ResponseEntity.ok(tokenResponse);

	}
}
