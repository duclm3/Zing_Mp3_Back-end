package com.example.zingmp3.controller;

import com.example.zingmp3.model.Artist;
import com.example.zingmp3.model.LikeArtist;
import com.example.zingmp3.model.User;
import com.example.zingmp3.service.artist.IArtistService;
import com.example.zingmp3.service.likeArtist.ILikeArtistService;
import com.example.zingmp3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/likes/artists/")
public class LikeArtistController {
    @Autowired
    ILikeArtistService likeArtistService;

    @Autowired
    UserService userService;

    @Autowired
    IArtistService artistService;

    @GetMapping("{id}")
    public ResponseEntity<?> GetLikeArtist(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        Optional<Artist> artist = artistService.findById(id);
        Optional<LikeArtist> likeArtist = likeArtistService.findByUserAndArtistId(currentUser, artist.get().getId());
        if(likeArtist.isPresent()){
            return new ResponseEntity<>(likeArtist.get().isLike(),HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }


    @PostMapping("{id}")
    public ResponseEntity<LikeArtist> createLikeArtist(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        Optional<Artist> artist = artistService.findById(id);
        Optional<LikeArtist> likeArtist = likeArtistService.findByUserAndArtistId(currentUser, artist.get().getId());
        //nếu có đối tượng like rồi kiểm tra trạng thái: nếu like rồi thì (isLike =true) thì chuyển từ true sang false và giảm like đi 1
        // nếu chưa like thì (isLike=false) và tăng số lượng like thêm 1
        if (likeArtist.isPresent() && likeArtist.get().getUser().equals(currentUser)) {
            if (likeArtist.get().isLike()) {
                likeArtist.get().setLike(false);
                if (artist.get().getLikes() > 0) {
                    artist.get().setLikes(artist.get().getLikes() - 1);
                }
            } else {
                likeArtist.get().setLike(true);
                artist.get().setLikes(artist.get().getLikes() + 1);
            }
            artistService.save(artist.get());
            return new ResponseEntity<>(likeArtistService.save(likeArtist.get()), HttpStatus.OK);
        }
// nếu không có đối tượng like thì tạo mới. Bấm lần đầu là like và tăng lượng likes lên 1
        LikeArtist newLikeArtist = new LikeArtist();
        newLikeArtist.setUser(currentUser);
        newLikeArtist.setArtist(artist.get());
        newLikeArtist.setLike(true);
        artist.get().setLikes(artist.get().getLikes() + 1);
        artistService.save(artist.get());
        return new ResponseEntity<>(likeArtistService.save(newLikeArtist), HttpStatus.OK);
    }
}

