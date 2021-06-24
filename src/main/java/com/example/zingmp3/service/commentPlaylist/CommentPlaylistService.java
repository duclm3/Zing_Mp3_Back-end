package com.example.zingmp3.service.commentPlaylist;

import com.example.zingmp3.model.CommentPlayList;
import com.example.zingmp3.repository.ICommentPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentPlaylistService implements ICommentPlaylistService {

    @Autowired
    ICommentPlaylistRepository commentPlaylistRepository;

    @Override
    public List<CommentPlayList> findAllByPlaylistId(Long id) {
        return commentPlaylistRepository.findAllByPlaylistId(id);
    }

    @Override
    public CommentPlayList save(CommentPlayList commentPlayList) {
        return commentPlaylistRepository.save(commentPlayList);
    }
}
