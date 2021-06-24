package com.example.zingmp3.repository;

import com.example.zingmp3.model.Artist;
import com.example.zingmp3.model.Song;
import com.example.zingmp3.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISongRepository extends JpaRepository<Song, Long> {
    Page<Song> findAllSongByStatusOrderByIdDesc(boolean status,Pageable pageable);

    List<Song> findAllSongByStatusOrderByIdDesc(boolean status);

    // Find Song by name
    List<Song> findAllByStatusAndNameSongContains(boolean status, String nameSong);

    @Query(value = "select * from song order by likes desc limit 6", nativeQuery = true)
    List<Song> findSongByStatusOrderByLikesDesc(boolean status);

    @Query(value = "select * from song order by views desc limit 5", nativeQuery = true)
    List<Song> findSongByStatusOrderByViewsDesc(boolean status);

    @Query(value = "select * from song order by id desc limit 5", nativeQuery = true)
    List<Song> findSongByStatusOrderByCreateAtDesc(boolean status);

    List<Song> findAllSongByStatusAndArtist(boolean status, Artist artist);
}
