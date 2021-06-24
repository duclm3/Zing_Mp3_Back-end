package com.example.zingmp3.controller;

import com.example.zingmp3.model.Artist;
import com.example.zingmp3.model.CommentPlayList;
import com.example.zingmp3.model.Playlist;
import com.example.zingmp3.model.User;
import com.example.zingmp3.service.commentPlaylist.ICommentPlaylistService;
import com.example.zingmp3.service.playlist.IPlaylistService;
import com.example.zingmp3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/comments/playlists/")
public class CommentPlaylistController {
    @Autowired
    ICommentPlaylistService commentPlaylistService;

    @Autowired
    UserService userService;

    @Autowired
    IPlaylistService playlistService;

    @GetMapping("show/{id}")
    public ResponseEntity<?> findAllByPlaylistId(@PathVariable Long id) {
        List<CommentPlayList> commentPlaylists = commentPlaylistService.findAllByPlaylistId(id);
        if (commentPlaylists.isEmpty()) {
            return new ResponseEntity<>("NO CONTENT", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentPlaylists, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<CommentPlayList> createCommentPlaylist(@PathVariable Long id, @RequestBody CommentPlayList commentPlayList) {
        User currentUser = userService.getCurrentUser();
        Optional<Playlist> playlist = playlistService.findById(id);
        commentPlayList.setUser(currentUser);
        commentPlayList.setPlaylist(playlist.get());
        return new ResponseEntity<>(commentPlaylistService.save(commentPlayList), HttpStatus.OK);
    }
}

