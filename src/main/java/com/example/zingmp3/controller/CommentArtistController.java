package com.example.zingmp3.controller;

import com.example.zingmp3.model.*;
import com.example.zingmp3.service.artist.IArtistService;
import com.example.zingmp3.service.commentArtist.ICommentArtistService;
import com.example.zingmp3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/comments/artists/")
public class CommentArtistController {
    @Autowired
    ICommentArtistService commentArtistService;

    @Autowired
    UserService userService;

    @Autowired
    IArtistService artistService;
    @GetMapping("show/{id}")
    public ResponseEntity<?> findAllByArtistId(@PathVariable Long id) {
        List<CommentArtist> commentArtists = commentArtistService.findAllByArtistId(id);
        if (commentArtists.isEmpty()) {
            return new ResponseEntity<>("NO CONTENT", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentArtists, HttpStatus.OK);
    }

    @PostMapping("{id}")
    public ResponseEntity<CommentArtist> createCommentArtist(@PathVariable Long id, @RequestBody CommentArtist commentArtist) {
        User currentUser = userService.getCurrentUser();
        Optional<Artist> artist = artistService.findById(id);
        commentArtist.setUser(currentUser);
        commentArtist.setArtist(artist.get());
        return new ResponseEntity<>(commentArtistService.save(commentArtist), HttpStatus.OK);
    }
}

