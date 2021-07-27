package com.example.dvdrental.repository;

import com.example.dvdrental.entity.Film;
import com.example.dvdrental.exception.IdNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FilmRepositoryTest {

    @Autowired
    FilmRepository filmRepository;
    
    @Test
    @DisplayName("전체 조회 테스트 - 리스트 갯수 검사")
    public void getFilms_ExpectedListSize1002() {

        int expectedListSize = 1002;

        List<Film> targetList = filmRepository.findAll();

        assertEquals(expectedListSize, targetList.size());

    }

    @ParameterizedTest
    @CsvSource({"1, Academy Dinosaur","12,Alaska Phantom"})
    @DisplayName("단일 조회 테스트")
    public void getFilm_targetFilmId_verifyTitle(int targetFilmId, String expectedTitle) {

        Film target = (filmRepository.findById(targetFilmId))
                .orElseThrow(()->new IdNotFoundException(targetFilmId));

        assertEquals(expectedTitle, target.getTitle());

    }
}