package com.example.zingmp3.repository;

import com.example.zingmp3.model.CommentArtist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentArtistRepository extends JpaRepository<CommentArtist,Long> {
    List<CommentArtist> findAllByArtistId(Long id);
}
