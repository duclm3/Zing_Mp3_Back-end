package com.example.zingmp3.service.likeArtist;

import com.example.zingmp3.model.LikeArtist;
import com.example.zingmp3.model.User;
import com.example.zingmp3.repository.ILikeArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class LikeArtistService implements ILikeArtistService {

    @Autowired
    ILikeArtistRepository likeArtistRepository;

    @Override
    public Optional<LikeArtist> findByUserAndArtistId(User user, Long id) {
        return likeArtistRepository.findByUserAndArtistId(user,id);
    }

    @Override
    public LikeArtist save(LikeArtist likeArtist) {
        return likeArtistRepository.save(likeArtist);
    }
}
