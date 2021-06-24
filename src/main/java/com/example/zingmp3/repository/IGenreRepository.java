package com.example.zingmp3.repository;

import com.example.zingmp3.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenreRepository extends JpaRepository<Genre,Long> {
}
