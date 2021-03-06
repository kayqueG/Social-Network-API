package com.kayque.socialnetwork.services;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;

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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	private final PasswordEncoder passwordEncoder;

	public UserDto signUp(SignUpDto userDto) {

		User user = userMapper.signUpToUser(userDto);
		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

		User savedUser = userRepository.save(user);

		return userMapper.toUserDto(savedUser);
	}

	private User getUser(Long id) {

		return userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
	}

	public ProfileDto getProfile(Long userId) {

		User user = getUser(userId);
		return userMapper.userToProfileDto(user);
	}

	public List<UserSummaryDto> searchUsers(String term) {

		List<User> users = userRepository.search("%" + term + "%");

		List<UserSummaryDto> usersToBeReturned = new ArrayList<>();

		users.forEach(user -> usersToBeReturned
				.add(new UserSummaryDto(user.getId(), user.getFirstName(), user.getLastName())));

		return usersToBeReturned;

	}

	public void addFriend(UserDto userDto, Long friendId) {

		User user = getUser(userDto.getId());

		User newFriend = getUser(friendId);

		if (user.getFriends() == null) {
			user.setFriends(new ArrayList<>());
		}

		user.getFriends().add(newFriend);

		userRepository.save(user);
	}

}
