package com.example.dvdrental.api.controller;

import com.example.dvdrental.api.assembler.FilmModelAssembler;
import com.example.dvdrental.api.representationmodel.FilmModel;
import com.example.dvdrental.entity.Actor;
import com.example.dvdrental.entity.Film;
import com.example.dvdrental.exception.FilmNotFoundException;
import com.example.dvdrental.exception.FilmTitleNotFoundException;
import com.example.dvdrental.exception.IdNotFoundException;
import com.example.dvdrental.service.ActorService;
import com.example.dvdrental.service.FilmService;
import com.example.dvdrental.util.CollectionChecker;
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
    ActorService actorService;

    @Autowired
    FilmModelAssembler filmModelAssembler;

    @GetMapping(path = "/all")
    @ApiOperation(value = "모든 영화 조회")
    public ResponseEntity<CollectionModel<FilmModel>> retrieveAllFilms() {

        final List<Film> films = filmService.getAllFilms();

        return ResponseEntity.ok(filmModelAssembler.toCollectionModel(films));

    }

    @GetMapping(path = "/id/{id}")
    @ApiOperation(value = "id로 영화 조회")
    public ResponseEntity<FilmModel> retrieveFilm(@PathVariable int id) {
        return filmService.getFilmById(id)
                .map(filmModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/{title}")
    @ApiOperation(value = "제목으로 영화 검색")
    public ResponseEntity<CollectionModel<FilmModel>> searchFilmByTitle(@PathVariable String title) {

        final List<Film> films = filmService.getFilmByTitle(title);

        if(CollectionChecker.isEmpty(films))
            throw new FilmTitleNotFoundException(title);

        return ResponseEntity.ok(filmModelAssembler.toCollectionModel(films));


    }

    @GetMapping(path = "/actor-id/{id}")
    @ApiOperation(value = "배우 id로 영화 검색")
    public ResponseEntity<CollectionModel<FilmModel>> searchFilmByActorId(@PathVariable int id) {

        final Actor actor = actorService.getActorById(id)
                .orElseThrow(()-> new IdNotFoundException(id));

        final List<Film> films = actor.getFilms();

        if(CollectionChecker.isEmpty(films))
            throw new FilmNotFoundException(id);

        return ResponseEntity.ok(filmModelAssembler.toCollectionModel(films));

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
