package com.example.zingmp3.repository;

import com.example.zingmp3.model.LikePlayList;
import com.example.zingmp3.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ILikePlaylistRepository extends JpaRepository<LikePlayList, Long> {
    Optional<LikePlayList> findByUserAndPlaylistId(User user, Long id);
}
