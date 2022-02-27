package com.kayque.socialnetwork.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kayque.socialnetwork.dto.ImageDto;
import com.kayque.socialnetwork.dto.MessageDto;
import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.entities.Image;
import com.kayque.socialnetwork.entities.Message;
import com.kayque.socialnetwork.entities.User;
import com.kayque.socialnetwork.exceptions.AppException;
import com.kayque.socialnetwork.mappers.MessageMapper;
import com.kayque.socialnetwork.mappers.UserMapper;
import com.kayque.socialnetwork.repositories.ImageRepository;
import com.kayque.socialnetwork.repositories.MessageRepository;
import com.kayque.socialnetwork.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityService {

	private static final int PAGE_SIZE = 10;

	private final MessageRepository messageRepository;

	private final ImageRepository imageRepository;

	private final UserMapper userMapper;

	private final UserRepository userRepository;

	private final MessageMapper messageMapper;

	public List<MessageDto> getCommunityMessages(UserDto userDto, int page) {

		User user = getUser(userDto);

		List<Long> friendsIds = Optional.of(user.getFriends())
				.map(friends -> friends.stream().map(User::getId).collect(Collectors.toList()))
				.orElse(Collections.emptyList());

		friendsIds.add(user.getId());

		List<Message> messages = messageRepository.findCommunityMessages(friendsIds, PageRequest.of(page, PAGE_SIZE));

		return messageMapper.messagesToMessageDtos(messages);
	}

	private User getUser(UserDto userDto) {
		return userRepository.findById(userDto.getId())
				.orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
	}

	public MessageDto postMessage(UserDto userDto, MessageDto messageDto) {
		User user = getUser(userDto);

		Message message = messageMapper.messageDtoToMessage(messageDto);
		message.setUser(user);

		if (user.getMessages() == null) {
			user.setMessages(new ArrayList<>());
		}

		user.getMessages().add(message);

		Message savedMessage = messageRepository.save(message);

		return messageMapper.messageToMessageDto(savedMessage);

	}

	public ImageDto postImage(UserDto userDto, String link, String title) throws IOException {

		User user = getUser(userDto);

		Image image = Image.builder().title(title).user(user).link(link)
				.createdDate(LocalDateTime.now(ZoneId.of("UTC"))).build();

		if (user.getImages() == null) {
			user.setImages(new ArrayList<>());
		}

		user.getImages().add(image);

		userRepository.save(user);

		return userMapper.imageToImageDto(image);

	}

	public List<ImageDto> getCommunityImages(UserDto userDto, int page) {
		User user = getUser(userDto);

		List<Long> friendIds = Optional.of(user.getFriends())
				.map(friends -> friends.stream().map(User::getId).collect(Collectors.toList()))
				.orElse(Collections.emptyList());

		friendIds.add(user.getId());

		List<Image> images = imageRepository.findCommunityImages(friendIds, PageRequest.of(page, PAGE_SIZE));

		return userMapper.imagesToImageDtos(images);

	}
}
