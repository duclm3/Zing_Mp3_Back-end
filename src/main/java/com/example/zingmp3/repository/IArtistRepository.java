package com.example.zingmp3.repository;

import com.example.zingmp3.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IArtistRepository extends JpaRepository<Artist,Long> {

    @Query(value = "select * from artist order by likes desc limit 6", nativeQuery = true)
    List<Artist> findAllOrderByLikesDesc();

    List<Artist> findAllByNameArtistContains(String nameArtist);
}
