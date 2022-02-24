package com.kayque.socialnetwork.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;

@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceImageGenerator")
	@GenericGenerator(name = "sequenceImageGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",

			parameters = { @Parameter(name = "sequence_name", value = "image_sequence"),
					@Parameter(name = "initial_value", value = "1000"),
					@Parameter(name = "increment_size", value = "1") })
	private Long id;

	@Column(nullable = false)
	@Size(max = 100)
	private String title;

	@Column(nullable = false)
	private String path;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@CreatedDate
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;

	public Image() {

	}

	public Image(Long id, @Size(max = 100) String title, String path, User user, LocalDateTime createdDate) {
		this.id = id;
		this.title = title;
		this.path = path;
		this.user = user;
		this.createdDate = createdDate;
	}
	
	public Image(Long id, @Size(max = 100) String title, String path, User user) {
		this.id = id;
		this.title = title;
		this.path = path;
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	

}