package com.example.zingmp3.service.artist;

import com.example.zingmp3.model.Artist;
import com.example.zingmp3.service.IGeneralService;

import java.util.List;

public interface IArtistService extends IGeneralService<Artist> {

    List<Artist> sortArtistByLike();

    List<Artist> findAllByNameArtist(String nameArtist);
}
