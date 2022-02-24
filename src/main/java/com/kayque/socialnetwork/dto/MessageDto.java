package com.kayque.socialnetwork.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MessageDto {

	private Long id;
	private String content;
	private UserSummaryDto userDto;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime createdDate;

	public MessageDto() {
		super();
	}

	public MessageDto(Long id, String content, UserSummaryDto userDto, LocalDateTime createdDate) {
		this.id = id;
		this.content = content;
		this.userDto = userDto;
		this.createdDate = createdDate;
	}

	public UserSummaryDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserSummaryDto userDto) {
		this.userDto = userDto;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
