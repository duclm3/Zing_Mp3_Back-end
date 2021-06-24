package com.example.zingmp3.service.commentSong;
import com.example.zingmp3.model.CommentSong;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICommentSongService {

    List<CommentSong> findAllByPlaylistId(Long id);

    CommentSong save(CommentSong commentSong);
}
