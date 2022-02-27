package com.kayque.socialnetwork.services;

import java.nio.CharBuffer;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kayque.socialnetwork.dto.CredentialsDto;
import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.entities.User;
import com.kayque.socialnetwork.exceptions.AppException;
import com.kayque.socialnetwork.mappers.UserMapper;
import com.kayque.socialnetwork.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final UserMapper userMapper;

	@Transactional
	public UserDto authenticate(CredentialsDto credentialsDto) {
		User user = userRepository.findByLogin(credentialsDto.getLogin()).orElse(null);

		if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
			log.debug("User {} authenticated correctly", credentialsDto.getLogin());
			return userMapper.toUserDto(user);
		}
		throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	}

	public UserDto findByLogin(String login) {
		User user = userRepository.findByLogin(login)
				.orElseThrow(() -> new AppException("Login not found", HttpStatus.NOT_FOUND));

		return userMapper.toUserDto(user);
	}

}
