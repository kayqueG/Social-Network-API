package com.kayque.socialnetwork.dto;

import java.time.LocalDateTime;

public class ImageDto {

	
	private Long id;
	private  String title;
	private String path;
	private UserSummaryDto userDto;
	private LocalDateTime createdDate;
	
	
	
	public ImageDto() {
		super();
	}
	
	
	public ImageDto(Long id, String title, String path, UserSummaryDto userDto, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.path = path;
		this.userDto = userDto;
		this.createdDate = createdDate;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
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
	
	
	
}
