package com.example.zingmp3.repository;

import com.example.zingmp3.model.LikeArtist;
import com.example.zingmp3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ILikeArtistRepository extends JpaRepository<LikeArtist, Long> {

    Optional<LikeArtist> findByUserAndArtistId(User user, Long id);
}
