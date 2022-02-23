package com.kayque.socialnetwork.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kayque.socialnetwork.dto.ProfileDto;
import com.kayque.socialnetwork.dto.UserSummaryDto;
import com.kayque.socialnetwork.services.UserService;

@RestController
@RequestMapping("/v1/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}/profile")
	public ResponseEntity<ProfileDto> getUserProfile(@PathVariable Long userId){
		return ResponseEntity.ok(userService.getProfile(userId));
	}
	
	 @PostMapping("/search")
	    public ResponseEntity<List<UserSummaryDto>> searchUsers(@RequestParam(value = "term") String term) {
	        return ResponseEntity.ok(userService.searchUsers(term));
	    }

}

