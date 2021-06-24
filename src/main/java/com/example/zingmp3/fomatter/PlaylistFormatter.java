package com.example.zingmp3.fomatter;

import com.example.zingmp3.model.Playlist;
import com.example.zingmp3.model.Song;
import com.example.zingmp3.service.playlist.IPlaylistService;
import com.example.zingmp3.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class PlaylistFormatter implements Formatter<Playlist> {
    @Autowired
    IPlaylistService playlistService;

    public PlaylistFormatter(ISongService songService) {
        this.playlistService = playlistService;
    }

    @Override
    public Playlist parse(String text, Locale locale) throws ParseException {
        Optional<Playlist> genreOptional= playlistService.findById(Long.parseLong(text));
        return genreOptional.orElse(null);
    }

    @Override
    public String print(Playlist object, Locale locale) {
        return null;
    }
}
