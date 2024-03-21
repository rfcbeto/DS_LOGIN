package br.com.digital.sorte.login.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.digital.sorte.login.data.dto.UserDTO;
import br.com.digital.sorte.login.data.model.User;
import br.com.digital.sorte.login.exceptions.UserNameNotFoundException;
import br.com.digital.sorte.login.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {


	@Autowired
	private UserRepository repository;
	
	ModelMapper modelMapper = new ModelMapper();

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

	public UserDTO findByUsername(String userName) {
		User user = repository.findByUsername(userName);
		user.getPermissions().forEach(p -> System.out.println(p.getDescription()));
		
		var userDTO = modelMapper.map(user, UserDTO.class);
		//userDTO.getPermissions().forEach(p -> System.out.println(p.getDescription()));
		return userDTO;
		
	}

	public UserDTO createNewUser(UserDTO userDTO) {

		User user = modelMapper.map(userDTO, User.class);
		//user.setId(null);
		User newUser = repository.save(user);
		UserDTO newUserDTO = modelMapper.map(newUser, UserDTO.class);
		return newUserDTO;
		//throw new UnsupportedOperationException("Unimplemented method 'createNewUser'");
	}
	

}
