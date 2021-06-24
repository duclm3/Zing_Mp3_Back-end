package com.example.zingmp3.service.song;

import com.example.zingmp3.model.Artist;
import com.example.zingmp3.model.Playlist;
import com.example.zingmp3.model.Song;
import com.example.zingmp3.model.User;
import com.example.zingmp3.repository.ISongRepository;
import com.example.zingmp3.service.playlist.IPlaylistService;
import com.example.zingmp3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class SongService implements ISongService {
    @Autowired
    ISongRepository songRepository;

    @Autowired
    UserService userService;

    @Autowired
    IPlaylistService playlistService;

    @Override
    public List<Song> findAllByStatus(boolean status) {
        return songRepository.findAllSongByStatusOrderByIdDesc(status);
    }

    @Override
    public Song save(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void remove(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Optional<Song> findById(Long id) {
        return songRepository.findById(id);
    }

    @Override
    public List<Song> sortByLike(boolean status) {
        return songRepository.findSongByStatusOrderByLikesDesc(status);
    }

    @Override
    public List<Song> sortByDate(boolean status) {
        return songRepository.findSongByStatusOrderByCreateAtDesc(status);
    }

    @Override
    public List<Song> songOfUser() {
        User currentUser = userService.getCurrentUser();
        Optional<Playlist> playlistRoot = playlistService.findById(currentUser.getPlaylistRootId());
        List<Song> songs = new ArrayList<>();
        if (playlistRoot.isPresent()) {
            for (Song song : playlistRoot.get().getSongs()) {
                if (song.getStatus().equals(true)) {
                    songs.add(song);
                    Collections.sort(songs, new Comparator<Song>() {
                        @Override
                        public int compare(Song o1, Song o2) {
                            return o2.getId() > o1.getId() ? 1 : -1;
                        }
                    });
                }
            }
            return songs;
        }
        return null;
    }

    @Override
    public List<Song> findAllByStatusAndNameSongContains(boolean status, String nameSong) {
        return songRepository.findAllByStatusAndNameSongContains(status,nameSong);
    }

    @Override
    public List<Song> findAllSongByStatusAndArtist(boolean status, Artist artist) {
        return songRepository.findAllSongByStatusAndArtist(status,artist);
    }
}
