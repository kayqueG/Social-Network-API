package com.kayque.socialnetwork.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kayque.socialnetwork.dto.ErrorDto;



@Component
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
	
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		MAPPER.writeValue(response.getOutputStream(),new ErrorDto("Unauthorized Path") );
	}

}
