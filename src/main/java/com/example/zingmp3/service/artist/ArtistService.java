package com.example.zingmp3.service.artist;

import com.example.zingmp3.model.Artist;
import com.example.zingmp3.repository.IArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService implements IArtistService {
    @Autowired
    IArtistRepository artistRepository;

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return artistRepository.findById(id);
    }

    @Override
    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public void delete(Long id) {
        artistRepository.deleteById(id);
    }

    @Override
    public List<Artist> sortArtistByLike() {
        return artistRepository.findAllOrderByLikesDesc();
    }

    @Override
    public List<Artist> findAllByNameArtist(String nameArtist) {
        return artistRepository.findAllByNameArtistContains(nameArtist);
    }
}
