package com.example.zingmp3.controller;

import com.example.zingmp3.model.*;
import com.example.zingmp3.service.likeSong.ILikeSongService;
import com.example.zingmp3.service.song.ISongService;
import com.example.zingmp3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/likes/songs/")
public class LikeSongController {
    @Autowired
    ILikeSongService likeSongService;

    @Autowired
    UserService userService;

    @Autowired
    ISongService songService;

    @GetMapping("{id}")
    public ResponseEntity<?> GetLikeArtist(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        Optional<Song> song = songService.findById(id);
        Optional<LikeSong> likeSong = likeSongService.findByUserAndSongId(currentUser, song.get().getId());
        if(likeSong.isPresent()){
            return new ResponseEntity<>(likeSong.get().isLike(),HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }



    @PostMapping("{id}")
    public ResponseEntity<?> createLikeSong(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        Optional<Song> song = songService.findById(id);

        if (song.isPresent()) {
            Optional<LikeSong> likeSong = likeSongService.findByUserAndSongId(currentUser, song.get().getId());
            //nếu có đối tượng like rồi kiểm tra trạng thái: nếu like rồi thì (isLike =true) thì chuyển từ true sang false và giảm like đi 1
            // nếu chưa like thì (isLike=false) và tăng số lượng like thêm 1
            if (likeSong.isPresent() && likeSong.get().getUser().equals(currentUser)) {
                if (likeSong.get().isLike()) {
                    likeSong.get().setLike(false);
                    if (song.get().getLikes() > 0) {
                        song.get().setLikes(song.get().getLikes() - 1);
                    }
                } else {
                    likeSong.get().setLike(true);
                    song.get().setLikes(song.get().getLikes() + 1);
                }
                songService.save(song.get());
                return new ResponseEntity<>(likeSongService.save(likeSong.get()), HttpStatus.OK);
            }
            // nếu không có đối tượng like thì tạo mới. Bấm lần đầu là like và tăng lượng likes lên 1
            LikeSong newLikeSong = new LikeSong();
            newLikeSong.setUser(currentUser);
            newLikeSong.setSong(song.get());
            newLikeSong.setLike(true);
            song.get().setLikes(song.get().getLikes() + 1);
            songService.save(song.get());
            return new ResponseEntity<>(likeSongService.save(newLikeSong), HttpStatus.OK);

        }
        else return new ResponseEntity<>("NO CONTENT",HttpStatus.NOT_FOUND);
    }
}

