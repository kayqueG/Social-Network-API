package com.kayque.socialnetwork.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kayque.socialnetwork.dto.SignUpDto;
import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.services.UserService;

@SpringBootTest
public class AuthenticationControllerTest {

	public MockMvc mockMvc;

	@Autowired
	public AuthenticationController authenticationController;

	@MockBean
	public UserService userService;

	private ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
	}
	
	
	@Test
	void testSignUp() throws  Exception {
		//given 
		SignUpDto signUpDto=SignUpDto.builder()
				.firstName("first")
				.lastName("last")
				.login("login")
				.password("pass".toCharArray())
				.build();
		
		when(userService.signUp(any()))
		.thenReturn(UserDto.builder().id(1L).firstName("first").lastName("last").token("token").build());
		
		//when then
		mockMvc.perform(post("/v1/signUp")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsBytes(signUpDto))
        ).andExpect(status().is(201))
                .andExpect(jsonPath("$.token", is("token")));
	}
}
