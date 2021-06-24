package com.example.zingmp3.controller;

import com.example.zingmp3.dto.PlaylistDto;
import com.example.zingmp3.model.*;
import com.example.zingmp3.service.likePlaylist.ILikePlaylistService;
import com.example.zingmp3.service.playlist.IPlaylistService;
import com.example.zingmp3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/likes/playlists/")
public class LikePlaylistController {
    @Autowired
    ILikePlaylistService likePlaylistService;

    @Autowired
    UserService userService;

    @Autowired
    IPlaylistService playlistService;

    @GetMapping("{id}")
    public ResponseEntity<?> GetLikeArtist(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        Optional<Playlist> playlist = playlistService.findById(id);
        Optional<LikePlayList> likePlayList = likePlaylistService.findByUserAndPlaylistId(currentUser, playlist.get().getId());
        if(likePlayList.isPresent()){
            return new ResponseEntity<>(likePlayList.get().isLike(),HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }


    @PostMapping("{id}")
    public ResponseEntity<?> createLikePlaylist(@PathVariable Long id) {
        User currentUser = userService.getCurrentUser();
        Optional<Playlist> playlist = playlistService.findById(id);

        if (playlist.isPresent()) {
            Optional<LikePlayList> likePlaylist = likePlaylistService.findByUserAndPlaylistId(currentUser, playlist.get().getId());
            //nếu có đối tượng like rồi kiểm tra trạng thái: nếu like rồi thì (isLike =true) thì chuyển từ true sang false và giảm like đi 1
            // nếu chưa like thì (isLike=false) và tăng số lượng like thêm 1
            if (likePlaylist.isPresent() && likePlaylist.get().getUser().equals(currentUser)) {
                if (likePlaylist.get().isLike()) {
                    likePlaylist.get().setLike(false);
                    if (playlist.get().getLikes() > 0) {
                        playlist.get().setLikes(playlist.get().getLikes() - 1);
                    }
                } else {
                    likePlaylist.get().setLike(true);
                    playlist.get().setLikes(playlist.get().getLikes() + 1);
                }
                playlistService.save(playlist.get());
                likePlaylistService.save(likePlaylist.get());
                PlaylistDto playlistDto = new PlaylistDto(playlist.get().getLikes());
                return new ResponseEntity<>(playlistDto, HttpStatus.OK);
            }
            // nếu không có đối tượng like thì tạo mới. Bấm lần đầu là like và tăng lượng likes lên 1
            LikePlayList newLikePlaylist = new LikePlayList();
            newLikePlaylist.setUser(currentUser);
            newLikePlaylist.setPlaylist(playlist.get());
            newLikePlaylist.setLike(true);
            playlist.get().setLikes(playlist.get().getLikes() + 1);
            playlistService.save(playlist.get());
            likePlaylistService.save(newLikePlaylist);
            PlaylistDto playlistDto = new PlaylistDto(playlist.get().getLikes());
            return new ResponseEntity<>(playlistDto, HttpStatus.OK);

        } else return new ResponseEntity<>("NO CONTENT", HttpStatus.NOT_FOUND);
    }
}

