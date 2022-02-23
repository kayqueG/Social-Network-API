package com.kayque.socialnetwork.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kayque.socialnetwork.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

	List<Message> findByUserId(Long id);
}
