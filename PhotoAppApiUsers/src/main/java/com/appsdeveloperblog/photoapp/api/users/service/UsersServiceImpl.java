package com.appsdeveloperblog.photoapp.api.users.service;

import java.util.ArrayList;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.photoapp.api.users.entity.UserEntity;
import com.appsdeveloperblog.photoapp.api.users.entity.UserRepository;
import com.appsdeveloperblog.photoapp.api.users.shared.UserDto;

@Service
public class UsersServiceImpl implements UsersService {
	
	UserRepository userrepo;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UsersServiceImpl(UserRepository userrepo) {
		this.userrepo = userrepo;
	}
	
	@Override
	public UserDto createUser(UserDto userdetails) {
		
		//generating unique id for each user
		userdetails.setUserid("2345");//UUID.randomUUID().toString()
		userdetails.setEncryptedpassword((userdetails.getPassword()));//bCryptPasswordEncoder.encode
		ModelMapper modelMapper = new ModelMapper();
		
		//setting strict rules to match all fields of the bojects
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		//map UserData transfer object to Entity class
		UserEntity userentitymapper = modelMapper.map(userdetails,UserEntity.class);
		
		
		//save the entity(table) on to the database
		userrepo.save(userentitymapper);
		UserDto returnvalue = modelMapper.map(userentitymapper, UserDto.class);
		
		return returnvalue;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userentity= userrepo.findByEmail(username);
		if(userentity == null) throw new UsernameNotFoundException(username);
		
		return new User(userentity.getEmail(),userentity.getEncryptedpassword(),true,true,true,true,new ArrayList<>());
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		
		UserEntity userentity= userrepo.findByEmail(email);
		if(userentity == null) throw new UsernameNotFoundException(email);
		
		
		return new ModelMapper().map(userentity, UserDto.class);
	}
	
	
	
	
}
