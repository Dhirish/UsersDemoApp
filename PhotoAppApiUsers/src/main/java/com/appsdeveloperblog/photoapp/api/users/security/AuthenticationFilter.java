package com.appsdeveloperblog.photoapp.api.users.security;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsdeveloperblog.photoapp.api.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.shared.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import coms.appsdeveloperblog.photoapp.api.users.ui.model.LoginRequestModel;
import io.jsonwebtoken.Jwts;



public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private UsersService usersService;
	private Environment environment;
	
	
	public AuthenticationFilter(UsersService usersService,Environment environment,AuthenticationManager authenticationManager) {
		this.usersService = usersService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,HttpServletResponse res) throws AuthenticationException{
		try {
			LoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(),LoginRequestModel.class);
			
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getemail(),creds.getPassword(),new ArrayList<>()));
			
		}catch(IOException io) {
			 throw new RuntimeException(io);
		}
		
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth) throws IOException,ServletException{
		
		String userName=((User)auth.getPrincipal()).getUsername();
		UserDto userdto= usersService.getUserDetailsByEmail(userName);
	
	}

}
