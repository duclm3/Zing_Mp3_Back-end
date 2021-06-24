package com.example.zingmp3.service.likePlaylist;
import com.example.zingmp3.model.LikePlayList;
import com.example.zingmp3.model.User;
import com.example.zingmp3.repository.ILikePlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikePlaylistService implements ILikePlaylistService {

    @Autowired
    ILikePlaylistRepository likePlaylistRepository;

    @Override
    public Optional<LikePlayList> findByUserAndPlaylistId(User user, Long id) {
        return likePlaylistRepository.findByUserAndPlaylistId(user,id);
    }

    @Override
    public LikePlayList save(LikePlayList likePlayList) {
        return likePlaylistRepository.save(likePlayList);
    }
}
