package com.example.zingmp3.service.likeArtist;

import com.example.zingmp3.model.LikeArtist;
import com.example.zingmp3.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public interface ILikeArtistService {

    Optional<LikeArtist> findByUserAndArtistId(User user, Long id);

    LikeArtist save(LikeArtist likeArtist);
}
