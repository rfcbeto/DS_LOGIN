package br.com.digital.sorte.login.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.digital.sorte.login.exceptions.UserNameNotFoundException;
import br.com.digital.sorte.login.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Find one user by name " + username +"! ");
		var user = repository.findByUsername(username);
		if(user != null) {
			return user;
		} else {
			throw new UserNameNotFoundException("User name " + username + " not found!");
		}
		
		
	}
	

}
