package com.kayque.socialnetwork.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kayque.socialnetwork.config.UserAuthenticationProvider;
import com.kayque.socialnetwork.dto.SignUpDto;
import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AuthenticationController {

	private final UserService userService;
	private final UserAuthenticationProvider userAuthenticationProvider;

	@PostMapping("/signUp")
	public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto user) {
		UserDto createdUser = userService.signUp(user);
		return ResponseEntity.created(URI.create("/users/" + createdUser.getId() + "/profile")).body(createdUser);
	}

	@PostMapping("/signIn")
	public ResponseEntity<UserDto> signIn(@AuthenticationPrincipal UserDto user) {
		user.setToken(userAuthenticationProvider.createToken(user.getLogin()));

		return ResponseEntity.ok(user);
	}

	@PostMapping("/signOut")
	public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDto user) {
		SecurityContextHolder.clearContext();
		return ResponseEntity.noContent().build();
	}

}
