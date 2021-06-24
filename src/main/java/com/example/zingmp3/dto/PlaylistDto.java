package com.example.zingmp3.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistDto {
    Long id;
    Long likes;

    public PlaylistDto(Long likes) {
        this.likes = likes;
    }
}
