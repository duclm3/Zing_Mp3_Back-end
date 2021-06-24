package com.example.zingmp3.service.genre;

import com.example.zingmp3.model.Genre;

import com.example.zingmp3.repository.IGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class GenreService implements IGenreService{
    @Autowired
    IGenreRepository genreRepository;



    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Long id) {

    }
}
