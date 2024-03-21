package br.com.digital.sorte.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.sorte.login.data.dto.UserDTO;
import br.com.digital.sorte.login.data.model.User;
import br.com.digital.sorte.login.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/sd/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<UserDTO> findByUsername() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.findByUsername(user.getUserName());

        return new ResponseEntity(userDTO, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        
        UserDTO newUserDTO = userService.createNewUser(userDTO);

        return new ResponseEntity(newUserDTO, HttpStatus.OK);
    }
    
}
