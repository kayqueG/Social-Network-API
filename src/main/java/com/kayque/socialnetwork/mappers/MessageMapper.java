package com.kayque.socialnetwork.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.kayque.socialnetwork.dto.MessageDto;
import com.kayque.socialnetwork.entities.Message;


@Mapper(componentModel="spring",uses= {UserMapper.class})
public interface MessageMapper {

	
	List <MessageDto> messagesToMessageDtos(List<Message> messages);
	
	@Mapping(target="userDto",source="user")
	MessageDto messageToMessageDto(Message message);
	
	Message messageDtoToMessage(MessageDto messageDto);
}
