package com.kayque.socialnetwork.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

@Service
public class CommunityService {

	

    @Value("${app.nfs.path:/tmp}")
    private String nfsPath;
	
	private static final int PAGE_SIZE=10;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MessageMapper messageMapper;
	
	
	public List<MessageDto> getCommunityMessages(UserDto userDto,int page){
		
		User user = getUser(userDto);
		
		List<Long> friendsIds= Optional.of(user.getFriends())
				.map(friends->friends.stream().map(User::getId).collect(Collectors.toList()))
				.orElse(Collections.emptyList());
		
		friendsIds.add(user.getId());
		
		List<Message> messages = messageRepository.findCommunityMessages(friendsIds, PageRequest.of(page, PAGE_SIZE));
	
		
		return messageMapper.messagesToMessageDtos(messages);
	}
	
	
	
	private User getUser(UserDto userDto) {
		return userRepository.findById(userDto.getId())
				.orElseThrow(()->new AppException("User not found",HttpStatus.NOT_FOUND));
	}

	
	public MessageDto postMessage(UserDto userDto,MessageDto messageDto) {
		User user=getUser(userDto);
		
		Message message= messageMapper.messageDtoToMessage(messageDto);
		message.setUser(user);
		
		if(user.getMessages()==null) {
			user.setMessages(new ArrayList<>());
		}
		
		user.getMessages().add(message);
		
		Message savedMessage=messageRepository.save(message);
		
		return messageMapper.messageToMessageDto(savedMessage);
		
	}
	public ImageDto postImage(UserDto userDto,MultipartFile file,String title) throws IOException {
		
		User user=getUser(userDto);
		
		String path=this.nfsPath
				+"/"+user.getId()
				+"/"+UUID.randomUUID()
				+"-"+file.getOriginalFilename();
		
		Image image= new Image(null,title,path,user);
		
		File storedImage= new File(path);
		
		if(!storedImage.getParentFile().exists()) {
			storedImage.getParentFile().mkdir();
		}
		
		storedImage.createNewFile();
		file.transferTo(storedImage);
		
		if(user.getImages()==null) {
			user.setImages(new ArrayList<>());
		}
		
		user.getImages().add(image);
		
		userRepository.save(user);
		
		
		return userMapper.imageToImageDto(image);
		
	
	}
	
	public List<ImageDto> getCommunityImages(UserDto userDto,int page){
		User user = getUser(userDto);
		
		   List<Long> friendIds = Optional.of(user.getFriends())
	                .map(friends -> friends.stream().map(User::getId).collect(Collectors.toList()))
	                .orElse(Collections.emptyList());
		   
		   friendIds.add(user.getId());
		   
		   List<Image> images = imageRepository.findCommunityImages(friendIds,
	                PageRequest.of(page, PAGE_SIZE));
		   
		   return userMapper.imagesToImageDtos(images);
		
		
	}
}
