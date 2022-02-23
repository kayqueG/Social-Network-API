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
public class Message {

	 @Id
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceMessageGenerator")
	    @GenericGenerator(
	            name = "sequenceMessageGenerator",
	            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	            parameters = {
	                    @Parameter(name = "sequence_name", value = "message_sequence"),
	                    @Parameter(name = "initial_value", value = "1000"),
	                    @Parameter(name = "increment_size", value = "1")
	            }
	    )
	    private Long id;

	    @Column(nullable = false)
	    @Size(max = 500)
	    private String content;

	    @ManyToOne(fetch = FetchType.LAZY)
	    private User user;

	    @CreatedDate
	    @Column(name = "created_date", nullable = false)
	    private LocalDateTime createdDate;
	
}
