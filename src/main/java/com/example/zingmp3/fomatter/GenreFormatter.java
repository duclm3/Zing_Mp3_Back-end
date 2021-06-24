package com.example.zingmp3.fomatter;


import com.example.zingmp3.model.Genre;
import com.example.zingmp3.service.genre.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;


public class GenreFormatter implements Formatter<Genre> {
    @Autowired
    IGenreService genreService;

    public GenreFormatter(IGenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public Genre parse(String text, Locale locale) throws ParseException {
        Optional<Genre> genreOptional=genreService.findById(Long.parseLong(text));
        return genreOptional.orElse(null);
    }

    @Override
    public String print(Genre object, Locale locale) {
        return null;
    }
}
