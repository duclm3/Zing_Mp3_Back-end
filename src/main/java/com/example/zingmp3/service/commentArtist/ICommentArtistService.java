package com.example.zingmp3.service.commentArtist;
import com.example.zingmp3.model.CommentArtist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICommentArtistService {

    List<CommentArtist> findAllByArtistId(Long id);

    CommentArtist save(CommentArtist commentArtist);
}
