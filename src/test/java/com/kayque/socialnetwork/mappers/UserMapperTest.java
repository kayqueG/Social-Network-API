package com.kayque.socialnetwork.mappers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.entities.User;
import com.kayque.socialnetwork.mappers.UserMapper;
import com.kayque.socialnetwork.mappers.UserMapperImpl;

public class UserMapperTest {

	private static UserMapper mapper;
	
	@BeforeAll
	public static void setUp() {
		mapper=new UserMapperImpl();
	}
	
	
	@Test
	void testUserMapper() {
		
		//given
		User user = User.builder()
				.id(1L)
				.firstName("first")
				.lastName("login")
				.password("pass")
				.createdDate(LocalDateTime.now())
				.build();
				
		
		//when
		UserDto userDto= mapper.toUserDto(user);
		
		//then
		 assertAll(
	                () -> {
	                    assertEquals(user.getFirstName(), userDto.getFirstName());
	                    assertEquals(user.getLastName(), userDto.getLastName());
	                }
	        );
		
	}
}
