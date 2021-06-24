package com.example.zingmp3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameArtist;

    private String gender;
    @Lob
    private String description;

    private Date dob;

    private Long likes = 0L;

    private String avatar;

    @OneToMany
    private List<Song> songs;

    public Artist(String nameArtist) {
        this.nameArtist = nameArtist;
    }
}
