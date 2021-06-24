package com.example.zingmp3.repository;

import com.example.zingmp3.model.CommentSong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentSongRepository extends JpaRepository<CommentSong,Long> {
    List<CommentSong> findAllBySongId(Long id);
}
