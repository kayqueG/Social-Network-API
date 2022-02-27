package com.kayque.socialnetwork.dto;

import java.time.LocalDateTime;

public class ImageDto {

	
	private Long id;
	private  String title;
	private String link;
	private UserSummaryDto userDto;
	private LocalDateTime createdDate;
	
	
	
	public ImageDto() {
	}
	
	
	public ImageDto(Long id, String title, String link, UserSummaryDto userDto, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.link = link;
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


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
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
