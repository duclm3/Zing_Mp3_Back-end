package com.example.zingmp3.service.likeSong;

import com.example.zingmp3.model.LikeSong;
import com.example.zingmp3.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public interface ILikeSongService {

    Optional<LikeSong> findByUserAndSongId(User user, Long id);

    LikeSong save(LikeSong likeSong);
}
