package com.example.zingmp3.service.playlist;

import com.example.zingmp3.model.Playlist;
import com.example.zingmp3.model.Song;
import com.example.zingmp3.model.User;
import com.example.zingmp3.repository.IPlaylistRepository;
import com.example.zingmp3.repository.ISongRepository;
import com.example.zingmp3.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements IPlaylistService{
    @Autowired
    private IPlaylistRepository playlistRepository;

    @Autowired
    private ISongRepository songRepository;

    @Autowired
    private IUserService userService;
    @Override
    public List<Playlist> findAllByStatus(boolean status) {
        List<Playlist> playlists = playlistRepository.findAllByStatusOrderByIdDesc(true);
        return playlists;
    }

    @Override
    public Optional<Playlist> findById(Long id) {
        return playlistRepository.findById(id);
    }


    @Override
    public Playlist save(Playlist playlist) {
        Date date = new java.util.Date();
        User user = userService.getCurrentUser();
        playlist.setUser(user);
        playlist.setCreateAt(date);
        return playlistRepository.save(playlist);
    }


// lay ra danh sach playlist moi nhat
    @Override
    public List<Playlist> findAllByCreatedTimeOrderByCreatedTime() {
        return playlistRepository.findAllByCreatedTimeOrderByCreatedTime(true);
    }

    @Override
    public Playlist addSongToPlaylist(Long idSong, Long idPlaylist) {
        Song song = songRepository.findById(idSong).get();
        Playlist playlist = playlistRepository.findById(idPlaylist).get();
        List<Song> songs = playlist.getSongs();
        if (songs.contains(song)) {
            return null;
        }
        songs.add(song);
        playlist.setSongs(songs);
        playlistRepository.save(playlist);
        return playlist;
    }

    // lay ra top 5 nghe nhieu nhat
    @Override
    public List<Playlist> findAllByViewsOrderByViews() {
        return playlistRepository.findAllByViewsOrderByViews(true);
    }

    @Override
    public List<Playlist>findPlaylistByUserAndStatusOrderByIdDesc(User user, boolean status) {
        return playlistRepository.findPlaylistByUserAndStatusOrderByIdDesc(user,status);
    }

    @Override
    public List<Playlist> findAllByStatusAndNamePlaylistContains(boolean status, String name) {
        return playlistRepository.findAllByStatusAndNamePlaylistContains(status, name);
    }

}
