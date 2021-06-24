package com.example.zingmp3.fomatter;

import com.example.zingmp3.model.Song;
import com.example.zingmp3.service.song.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

public class SongFormatter implements Formatter<Song> {
    @Autowired
    ISongService songService;

    public SongFormatter(ISongService songService) {
        this.songService = songService;
    }

    @Override
    public Song parse(String text, Locale locale) throws ParseException {
        Optional<Song> genreOptional= songService.findById(Long.parseLong(text));
        return genreOptional.orElse(null);
    }

    @Override
    public String print(Song object, Locale locale) {
        return null;
    }
}
