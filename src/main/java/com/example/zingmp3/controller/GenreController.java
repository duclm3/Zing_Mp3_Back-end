package com.example.zingmp3.controller;
import com.example.zingmp3.service.genre.IGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    IGenreService genreService;

    @GetMapping
    public ResponseEntity<?> getAllSong() {
        return new ResponseEntity<>(genreService.findAll(), HttpStatus.OK);
    }
}
