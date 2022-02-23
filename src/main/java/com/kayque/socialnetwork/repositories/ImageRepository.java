package com.kayque.socialnetwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kayque.socialnetwork.entities.Image;

public interface ImageRepository  extends JpaRepository<Image,Long>{

}
