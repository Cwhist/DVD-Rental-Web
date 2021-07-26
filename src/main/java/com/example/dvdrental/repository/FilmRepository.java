package com.example.dvdrental.repository;

import com.example.dvdrental.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query("SELECT film FROM Film film WHERE film.title LIKE %:title%")
    List<Film> searchByTitleLike(@Param("title") String title);

}
