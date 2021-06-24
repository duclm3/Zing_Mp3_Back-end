package com.example.zingmp3.service.playlist;

import com.example.zingmp3.model.Playlist;
import com.example.zingmp3.model.User;

import java.util.List;
import java.util.Optional;


public interface IPlaylistService {
    List<Playlist> findAllByStatus(boolean status);

    Optional<Playlist> findById(Long id);


    Playlist save(Playlist playlist);


    // lay ra top 5 playlist moi nhat
    List<Playlist> findAllByCreatedTimeOrderByCreatedTime();

    Playlist addSongToPlaylist(Long idSong, Long idPlaylist);

    // lay ra top 5 playlist nghe nhieu nhat
    List<Playlist> findAllByViewsOrderByViews();

    List<Playlist>findPlaylistByUserAndStatusOrderByIdDesc(User user, boolean status);

    List<Playlist> findAllByStatusAndNamePlaylistContains(boolean status, String name);

}
