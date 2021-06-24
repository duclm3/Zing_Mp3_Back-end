package com.example.zingmp3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namePlaylist;

    @Lob
    private String description;

    @ManyToOne
    private Genre genre;

    private String image;

    private Date createAt;

    private Date editAt;

    private Boolean status = false;

    private Long views = 0L;

    private Long likes = 0L;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Song> songs = new ArrayList<>();

    @ManyToOne
    private User user;

}
