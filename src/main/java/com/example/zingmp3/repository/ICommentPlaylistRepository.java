package com.example.zingmp3.repository;

import com.example.zingmp3.model.CommentPlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentPlaylistRepository extends JpaRepository<CommentPlayList,Long> {
    List<CommentPlayList> findAllByPlaylistId(Long id);
}
