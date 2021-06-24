package com.example.zingmp3.fomatter;

import com.example.zingmp3.model.Artist;
import com.example.zingmp3.service.artist.IArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;


public class ArtistFormatter implements Formatter<Artist> {
    @Autowired
    IArtistService artistService;

    public ArtistFormatter(IArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public Artist parse(String text, Locale locale) throws ParseException {
        Optional<Artist> genreOptional= artistService.findById(Long.parseLong(text));
        return genreOptional.orElse(null);
    }

    @Override
    public String print(Artist object, Locale locale) {
        return null;
    }
}
