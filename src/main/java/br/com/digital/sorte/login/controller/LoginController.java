package br.com.digital.sorte.login.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sd")
public class LoginController {

	
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	@GetMapping("/ola")
	public ResponseEntity<?> greeting(@RequestParam(value="name", defaultValue = "world") String name) {
		return new ResponseEntity<>(counter.incrementAndGet() + " - " + String.format(template, name), HttpStatus.OK);
	}
	
}
