package com.kayque.socialnetwork.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kayque.socialnetwork.dto.ImageDto;
import com.kayque.socialnetwork.dto.MessageDto;
import com.kayque.socialnetwork.dto.UserDto;
import com.kayque.socialnetwork.services.CommunityService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/community")
public class CommunityController {

	private final CommunityService communityService;

	@GetMapping("/messages")
	public ResponseEntity<List<MessageDto>> getCommunityMessages(@AuthenticationPrincipal UserDto user,
			@RequestParam(value = "page", defaultValue = "0") int page) {
		return ResponseEntity.ok(communityService.getCommunityMessages(user, page));
	}

	@GetMapping("/images")
	public ResponseEntity<List<ImageDto>> getCommunityImages(@AuthenticationPrincipal UserDto userDto,
			@RequestParam(value = "page", defaultValue = "0") int page) {

		return ResponseEntity.ok(communityService.getCommunityImages(userDto, page));

	}

	@PostMapping("/messages")
	public ResponseEntity<MessageDto> postMessage(@AuthenticationPrincipal UserDto user,
			@RequestBody MessageDto messageDto) {

		return ResponseEntity.created(URI.create("/v1/community/messages"))
				.body(communityService.postMessage(user, messageDto));

	}

	@PostMapping("/images")
	public ResponseEntity<ImageDto> postImage(@AuthenticationPrincipal UserDto user, @RequestBody ImageDto image,
			@RequestParam(value = "title") String title) throws IOException {

		return ResponseEntity.created(URI.create("/v1/community/images"))
				.body(communityService.postImage(user, image.getLink(), title));
	}

}
