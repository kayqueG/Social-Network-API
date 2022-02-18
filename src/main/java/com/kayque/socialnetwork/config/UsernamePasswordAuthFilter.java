package com.kayque.socialnetwork.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kayque.socialnetwork.dto.CredentialsDto;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {

	
	private final UserAuthenticationProvider userAuthenticationProvider; 
	
	private static final ObjectMapper MAPPER= new ObjectMapper();
	
	public UsernamePasswordAuthFilter(UserAuthenticationProvider userAuthenticationProvider) {
		this.userAuthenticationProvider=userAuthenticationProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest,
									HttpServletResponse httpServletResponse,
									FilterChain filterChain) throws ServletException, IOException {

		if ("/v1/signIn".equals(httpServletRequest.getServletPath())
				&& HttpMethod.POST.matches(httpServletRequest.getMethod())) {
			CredentialsDto credentialsDto = MAPPER.readValue(httpServletRequest.getInputStream(), CredentialsDto.class);

			try {
				SecurityContextHolder.getContext()
						.setAuthentication(userAuthenticationProvider.validateCredentials(credentialsDto));
			} catch (RuntimeException e) {
				SecurityContextHolder.clearContext();
				throw e;
			}
		}

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
		
	


}

