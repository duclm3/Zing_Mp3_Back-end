package com.example.zingmp3.controller;

import com.example.zingmp3.model.Playlist;
import com.example.zingmp3.model.Song;
import com.example.zingmp3.model.User;
import com.example.zingmp3.service.playlist.IPlaylistService;
import com.example.zingmp3.service.song.ISongService;
import com.example.zingmp3.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    UserService userService;


    @Autowired
    IPlaylistService playlistService;

    @Autowired
    ISongService songService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllPlayList() {
        List<Playlist> playlists = playlistService.findAllByStatus(true);
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        String imageDefault = "https://firebasestorage.googleapis.com/v0/b/zingmp3-4bcaf.appspot.com/o/zqcsp5iv63?alt=media&token=c1dc01ec-7c29-43cd-b9e1-63f5a2699f4e";
        if (playlist.getImage().isEmpty()) {
            playlist.setImage(imageDefault);
        }
        playlist.setStatus(true);
        return new ResponseEntity<>(playlistService.save(playlist), HttpStatus.CREATED);
    }

    @GetMapping(value = "/news", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Playlist>> getAllRatingsPlaylist() {
        return new ResponseEntity<>(playlistService.findAllByCreatedTimeOrderByCreatedTime(), HttpStatus.OK);
    }

    @GetMapping("/user/{idPlaylist}/songs/{idSong}")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idSong") Long idSong) {
        return new ResponseEntity<>(playlistService.addSongToPlaylist(idSong, idPlaylist), HttpStatus.OK);
    }

    @GetMapping(value = "/topview", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Playlist>> getAllTopView() {
        return new ResponseEntity<>(playlistService.findAllByViewsOrderByViews(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editPlaylist(@PathVariable Long id, @RequestBody Playlist playlist) {
        Optional<Playlist> playlistOptional = playlistService.findById(id);
        if (playlist.getImage().isEmpty()) {
            playlist.setImage(playlistOptional.get().getImage());
        }
        playlist.setLikes(playlistOptional.get().getLikes());
        playlist.setViews(playlistOptional.get().getViews());
        playlist.setStatus(playlistOptional.get().getStatus());
        playlist.setEditAt(Date.valueOf(LocalDate.now()));
        return playlistOptional.map(playlist1 -> {
            playlist.setId(playlist1.getId());
            playlistService.save(playlist);
            return new ResponseEntity<>(playlist, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {
        Optional<Playlist> playList = playlistService.findById(id);
        if (!playList.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playList.get().setViews(playList.get().getViews() + 1);
        playlistService.save(playList.get());
        return new ResponseEntity<>(playList, HttpStatus.OK);
    }

    @GetMapping("/{id}/songs")
    public ResponseEntity<List<Song>> getAllSongById(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistService.findById(id);
        if (playlist.isPresent()) {
            List<Song> songs = playlist.get().getSongs();
            return new ResponseEntity<>(songs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/myPlaylist")
    public ResponseEntity<List<Playlist>> findAllByUser() {
        User currentUser = userService.getCurrentUser();
        List<Playlist> playlists = playlistService.findPlaylistByUserAndStatusOrderByIdDesc(currentUser, true);
        if (playlists.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Long id) {
        Optional<Playlist> playList = playlistService.findById(id);
        if (playList.isPresent()) {
            playList.get().setStatus(false);
            playlistService.save(playList.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{idPlaylist}/songs/{idSong}")
    public ResponseEntity<?> removeFromPlaylist(@PathVariable("idPlaylist") Long idPlaylist, @PathVariable("idSong") Long idSong) {
        User currentUser = userService.getCurrentUser();
        Optional<Playlist> playList = playlistService.findById(idPlaylist);
        Optional<Song> song = songService.findById(idSong);
        if (playList.isPresent() && song.isPresent() && playList.get().getUser().equals(currentUser)) {
            List<Song> songs = playList.get().getSongs();
            songs.remove(song.get());
            playList.get().setSongs(songs);
            playlistService.save(playList.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> getPlayListByName(@PathVariable("name") String namePlaylist) {
        List<Playlist> playlists = playlistService.findAllByStatusAndNamePlaylistContains(true, namePlaylist);
        if (playlists.isEmpty()) {
            return new ResponseEntity<>("NO CONTENT", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playlists, HttpStatus.OK);
    }

}
