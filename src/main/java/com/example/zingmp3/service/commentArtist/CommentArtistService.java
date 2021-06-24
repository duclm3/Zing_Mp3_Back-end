package com.example.zingmp3.service.commentArtist;

import com.example.zingmp3.model.CommentArtist;
import com.example.zingmp3.repository.ICommentArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentArtistService implements ICommentArtistService {

    @Autowired
    ICommentArtistRepository commentArtistRepository;

    @Override
    public List<CommentArtist> findAllByArtistId(Long id) {
        return commentArtistRepository.findAllByArtistId(id);
    }

    @Override
    public CommentArtist save(CommentArtist commentArtist) {
        return commentArtistRepository.save(commentArtist);
    }
}
