package com.example.zingmp3.repository;

import com.example.zingmp3.model.LikeSong;
import com.example.zingmp3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ILikeSongRepository extends JpaRepository<LikeSong, Long> {
    Optional<LikeSong> findByUserAndSongId(User user, Long id);
}
