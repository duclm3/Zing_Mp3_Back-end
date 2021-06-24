package com.example.zingmp3.service.likeSong;

import com.example.zingmp3.model.LikeSong;
import com.example.zingmp3.model.User;
import com.example.zingmp3.repository.ILikeSongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeSongService implements ILikeSongService {

    @Autowired
    ILikeSongRepository likeSongRepository;

    @Override
    public Optional<LikeSong> findByUserAndSongId(User user, Long id) {
        return likeSongRepository.findByUserAndSongId(user,id);
    }

    @Override
    public LikeSong save(LikeSong likeSong) {
        return likeSongRepository.save(likeSong);
    }
}
