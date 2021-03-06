package com.kayque.socialnetwork.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kayque.socialnetwork.dto.ProfileDto;
import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.dto.UserSummaryDto;
import com.kayque.socialnetwork.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/{userId}/profile")
	public ResponseEntity<ProfileDto> getUserProfile(@PathVariable Long userId){
		return ResponseEntity.ok(userService.getProfile(userId));
	}
	
	@PostMapping("/friends/{friendId}")
	public ResponseEntity<Void> addFriend(@AuthenticationPrincipal UserDto userDto,@PathVariable Long friendId){
		
		userService.addFriend(userDto,friendId);
		
		return ResponseEntity.noContent().build();
	}
	
	 @PostMapping("/search")
	    public ResponseEntity<List<UserSummaryDto>> searchUsers(@RequestParam(value = "term") String term) {
	        return ResponseEntity.ok(userService.searchUsers(term));
	    }

}

