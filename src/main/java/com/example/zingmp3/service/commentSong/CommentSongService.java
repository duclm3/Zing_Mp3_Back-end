package com.example.zingmp3.service.commentSong;

import com.example.zingmp3.model.CommentSong;
import com.example.zingmp3.repository.ICommentSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentSongService implements ICommentSongService {

    @Autowired
    ICommentSongRepository commentSongRepository;

    @Override
    public List<CommentSong> findAllByPlaylistId(Long id) {
        return commentSongRepository.findAllBySongId(id);
    }

    @Override
    public CommentSong save(CommentSong commentSong) {
        return commentSongRepository.save(commentSong);
    }
}
