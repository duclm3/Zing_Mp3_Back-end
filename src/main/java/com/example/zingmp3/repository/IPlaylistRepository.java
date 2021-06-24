package com.example.zingmp3.repository;

import com.example.zingmp3.model.Playlist;
import com.example.zingmp3.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IPlaylistRepository extends JpaRepository<Playlist,Long> {

    Page<Playlist> findAllSongByStatus(boolean status, Pageable pageable);

    List<Playlist> findAllByStatusAndNamePlaylistContains(boolean status, String name);

    // lay ra top 5 bai hat moi nhat
    @Query(value = "select * from playlist where status = ? order by create_at desc limit 5", nativeQuery = true)
    List<Playlist> findAllByCreatedTimeOrderByCreatedTime(boolean status);


    // playlist nghe nhieu nhat
    @Query(value = "select * from playlist where status = ? order by views desc limit 5", nativeQuery = true)
    List<Playlist> findAllByViewsOrderByViews(boolean status);


    List<Playlist> findAllByUserOrderByIdDesc(User user);

    List<Playlist> findAllByStatusOrderByIdDesc(boolean status);

    List<Playlist> findPlaylistByUserAndStatusOrderByIdDesc(User user, boolean status);

}
