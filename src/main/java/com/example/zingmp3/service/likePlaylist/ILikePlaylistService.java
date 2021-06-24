package com.example.zingmp3.service.likePlaylist;

import com.example.zingmp3.model.LikePlayList;
import com.example.zingmp3.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface ILikePlaylistService {

    Optional<LikePlayList> findByUserAndPlaylistId(User user, Long id);

    LikePlayList save(LikePlayList likePlayList);
}
