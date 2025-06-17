package com.example.TaskProject.controller;

import com.example.TaskProject.securityService.JWTtokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskProject.payload.JwtResponse;
import com.example.TaskProject.payload.LoginDto;
import com.example.TaskProject.payload.UserDto;
import com.example.TaskProject.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Slf4j
public class UserController {
	
	private final  UserService userService;
	

	@Autowired
	private JWTtokenProvider jwttokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/user")
	ResponseEntity<UserDto> insertUser(@RequestBody  UserDto userDto){   
		return new ResponseEntity<> (userService.createUser(userDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	ResponseEntity<JwtResponse>  loginuser(@RequestBody LoginDto loginDto){
		       Authentication  authentication = 
				authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(), loginDto.getUserPassword())
				);
		       SecurityContextHolder.getContext().setAuthentication(authentication);
		      log.info("Authentication {}",authentication);
			  String token = jwttokenProvider.generateToken(authentication);
			  log.info("Token {}",token);
			  String mail =jwttokenProvider.getEmailFromToken(token);
			   log.info("mail {}", mail);
		return new ResponseEntity<JwtResponse> (new JwtResponse(token), HttpStatus.OK);
	}

}
