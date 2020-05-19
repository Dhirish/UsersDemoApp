package com.appsdeveloperblog.photoapp.api.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appsdeveloperblog.photoapp.api.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.shared.UserDto;

import coms.appsdeveloperblog.photoapp.api.users.ui.model.CreateUserRequestModel;
import coms.appsdeveloperblog.photoapp.api.users.ui.model.CreateUserResponsemodel;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UsersService userservice;
	
	@GetMapping("/status/check")
	public String status() {
		return "Working on port"+env.getProperty("local.server.port");
	}
	
	@PostMapping(
			consumes= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces= {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)
	public ResponseEntity<CreateUserResponsemodel> createuser(@Valid @RequestBody CreateUserRequestModel createrequest) {
		
		ModelMapper modelmapper = new ModelMapper();
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserDto userdto = modelmapper.map(createrequest,UserDto.class);		
		UserDto useresponse= userservice.createUser(userdto);
		
		CreateUserResponsemodel returnvalue = modelmapper.map(useresponse,CreateUserResponsemodel.class);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(returnvalue);
		
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
