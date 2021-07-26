package com.example.dvdrental.api.controller;

import com.example.dvdrental.api.assembler.FilmModelAssembler;
import com.example.dvdrental.api.representationmodel.FilmModel;
import com.example.dvdrental.entity.Film;
import com.example.dvdrental.exception.IdNotFoundException;
import com.example.dvdrental.service.FilmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping("/api/films")
@Api(tags = {"영화 CRUD API"})
public class FilmController {

    @Autowired
    FilmService filmService;

    @Autowired
    FilmModelAssembler filmModelAssembler;

    @GetMapping
    @ApiOperation(value = "모든 영화 조회")
    public ResponseEntity<CollectionModel<FilmModel>> retrieveAllFilms() {

        final List<Film> films = filmService.getAllFilms();

        return ResponseEntity.ok(filmModelAssembler.toCollectionModel(films));

    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "id로 영화 조회")
    public ResponseEntity<FilmModel> retrieveFilm(@PathVariable int id) {
        return filmService.getFilmById(id)
                .map(filmModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ApiOperation(value = "영화 추가")
    public ResponseEntity<FilmModel> insertNewFilm(@RequestBody @Valid FilmDto request) {

        Film film = Film.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .length(request.getLength())
                .rentalDuration(request.getRentalDuration())
                .replacementCost(request.getReplacementCost())
                .specialFeatures(request.getSpecialFeatures())
                .build();

        film = filmService.insertNewFilm(film);

        FilmModel filmModel = filmModelAssembler.toModel(film);

        return ResponseEntity.status(HttpStatus.CREATED).body(filmModel);

    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "영화 정보 수정")
    public ResponseEntity<FilmModel> updateFilm(@PathVariable int id, @RequestBody @Valid FilmDto request) {

        Film film = filmService.getFilmById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        // Update new info to older film
        film.setTitle(request.title);
        film.setDescription(request.description);
        film.setLength(request.getLength());
        film.setRentalDuration(request.getRentalDuration());
        film.setReplacementCost(request.getReplacementCost());
        film.setSpecialFeatures(request.getSpecialFeatures());

        filmService.updateFilm(film);

        return ResponseEntity.ok(filmModelAssembler.toModel(film));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "영화 정보 삭제")
    public ResponseEntity<Void> deleteFilm(@PathVariable int id) {

        final Film film = filmService.getFilmById(id)
                            .orElseThrow(()-> new IdNotFoundException(id));

        filmService.deleteFilm(film);

        return ResponseEntity.noContent().build();
    }


    @Getter
    @Setter
    static class FilmDto {

        @NotNull(message = "title is required")
        @Size(message = "title must be lower than 50", min=1, max=50)
        private String title;

        private String description;

        private int length;

        private int rentalDuration;

        private float replacementCost;

        private String specialFeatures;

    }
}
