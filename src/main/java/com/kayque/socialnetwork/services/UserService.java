package com.kayque.socialnetwork.services;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kayque.socialnetwork.dto.ProfileDto;
import com.kayque.socialnetwork.dto.SignUpDto;
import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.dto.UserSummaryDto;
import com.kayque.socialnetwork.entities.User;
import com.kayque.socialnetwork.mappers.UserMapper;
import com.kayque.socialnetwork.repositories.UserRepository;
import com.kayque.socialnetwork.exceptions.AppException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserDto signUp(SignUpDto userDto) {

		User user = userMapper.signUpToUser(userDto);
		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

		User savedUser = userRepository.save(user);

		log.info("Creating new user {}", userDto.getLogin());

		return userMapper.toUserDto(savedUser);
	}

	private User getUser(Long id) {

		return userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
	}

	public ProfileDto getProfile(Long userId) {
		
		User user= getUser(userId);
		return userMapper.userToProfileDto(user);
	}
	
	public List<UserSummaryDto> searchUsers(String term){
		
		List<User> users=userRepository.search("%"+ term + "%");
		
		List<UserSummaryDto> usersToBeReturned=new ArrayList<>();
		
		users.forEach(user->usersToBeReturned.add(new UserSummaryDto(user.getId(),user.getFirstName(),user.getLastName())));
			
		return usersToBeReturned;
	
	}
	
}
