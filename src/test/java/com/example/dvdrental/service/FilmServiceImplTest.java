package com.example.dvdrental.service;

import com.example.dvdrental.entity.Film;
import com.example.dvdrental.entity.Inventory;
import com.example.dvdrental.exception.IdNotFoundException;
import com.example.dvdrental.repository.FilmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilmServiceImplTest {

    @Autowired
    FilmService filmService;

    @ParameterizedTest
    @CsvSource({"1,8", "2,3", "3,4"})
    @DisplayName("받아온 영화의 Inventory 리스트 확인")
    public void getAllFilms_checkInventoryList(int filmId, int expectedSize) {

        Film target = filmService.getFilmById(filmId)
                        .orElseThrow(()-> new IdNotFoundException(filmId));

        List<Inventory> inventoryList = target.getInventoryList();

        assertEquals(expectedSize, inventoryList.size());

    }

}