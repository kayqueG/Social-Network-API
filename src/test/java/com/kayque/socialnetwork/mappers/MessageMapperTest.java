package com.kayque.socialnetwork.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.kayque.socialnetwork.dto.MessageDto;
import com.kayque.socialnetwork.entities.Message;
import com.kayque.socialnetwork.entities.User;
import com.kayque.socialnetwork.mappers.MessageMapper;
import com.kayque.socialnetwork.mappers.MessageMapperImpl;
import com.kayque.socialnetwork.mappers.UserMapper;
import com.kayque.socialnetwork.mappers.UserMapperImpl;

public class MessageMapperTest {

	
	private static MessageMapper messageMapper = new MessageMapperImpl();
	private static UserMapper userMapper= new UserMapperImpl();
	
	@BeforeAll
	public static void setUp() {
		ReflectionTestUtils.setField(messageMapper, "userMapper", userMapper);
	}
	
	@Test
	void testMapSingleMessage() {
		//given
		Message msg = Message.builder()
				.id(1L)
				.content("content")
				.user(User.builder().id(1L).firstName("first").lastName("last").build())
				.createdDate(LocalDateTime.now())
				.build();
		
		//when
		MessageDto msgDto = messageMapper.messageToMessageDto(msg);
		
		//then
		  assertAll(() -> {
	            assertEquals(msg.getContent(), msgDto.getContent());
	            assertEquals(msg.getUser().getId(), msgDto.getUserDto().getId());
	        });
				
	}
	
}
