package com.example.TaskProject.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TaskProject.entity.User;
import com.example.TaskProject.payload.UserDto;
import com.example.TaskProject.repo.UserRepository;
import com.example.TaskProject.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
			User user =userDtoToEntity( userDto);
			User saveUser=userRepo.save(user);	
		return EntityToUserDto(saveUser);
	}

	private UserDto EntityToUserDto(User saveUser) {
		UserDto userDto =new UserDto();
		     userDto.setUserId(saveUser.getUserId());
		     userDto.setUserName(saveUser.getUserName());
		     userDto.setUserEmail(saveUser.getUserEmail());
		     userDto.setUserPassword(saveUser.getUserPassword());
		return userDto;
	}

	private User userDtoToEntity(UserDto userDto) {
		User user =new User();
		user.setUserEmail(userDto.getUserEmail());
		user.setUserName(userDto.getUserName());
		user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		return user;
	}

}
