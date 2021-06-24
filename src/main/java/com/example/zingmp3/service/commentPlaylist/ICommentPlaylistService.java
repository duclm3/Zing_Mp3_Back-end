package com.example.zingmp3.service.commentPlaylist;
import com.example.zingmp3.model.CommentPlayList;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ICommentPlaylistService {

    List<CommentPlayList> findAllByPlaylistId(Long id);

    CommentPlayList save(CommentPlayList commentPlayList);
}
