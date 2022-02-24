package com.kayque.socialnetwork.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kayque.socialnetwork.entities.Image;

public interface ImageRepository  extends JpaRepository<Image,Long>{


    @Query(value = "SELECT img FROM Image img WHERE img.user.id IN :ids ORDER BY img.createdDate DESC")
    List<Image> findCommunityImages(@Param("ids") List<Long> ids, Pageable pageable);
}
