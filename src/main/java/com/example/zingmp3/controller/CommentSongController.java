package com.example.zingmp3.controller;

import com.example.zingmp3.model.*;
import com.example.zingmp3.service.commentSong.ICommentSongService;
import com.example.zingmp3.service.song.ISongService;
import com.example.zingmp3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/comments/songs/")
public class CommentSongController {
    @Autowired
    ICommentSongService commentSongService;

    @Autowired
    UserService userService;

    @Autowired
    ISongService songService;

    @GetMapping("show/{id}")
    public ResponseEntity<?> findAllBySongId(@PathVariable Long id) {
        List<CommentSong> commentSongs = commentSongService.findAllByPlaylistId(id);
        if (commentSongs.isEmpty()) {
            return new ResponseEntity<>("NO CONTENT", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentSongs, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<CommentSong> createCommentSong(@PathVariable Long id, @RequestBody CommentSong commentSong) {
        User currentUser = userService.getCurrentUser();
        Optional<Song> song = songService.findById(id);
        commentSong.setUser(currentUser);
        commentSong.setSong(song.get());
        return new ResponseEntity<>(commentSongService.save(commentSong), HttpStatus.OK);
    }
}

