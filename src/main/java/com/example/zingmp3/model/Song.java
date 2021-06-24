package com.example.zingmp3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameSong;
    @Lob
    private String description;
    @Lob
    private String imageUrl;
    @Lob
    private String songUrl;

    private Date createAt;

    private Date editAt;

    private String album;
    //delete
    private Boolean status = true;

    private Long likes = 0L;

    private Long views = 0L;

    private  String author;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private Genre genre ;
}
