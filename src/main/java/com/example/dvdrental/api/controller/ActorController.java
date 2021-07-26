package com.example.dvdrental.api.controller;

import com.example.dvdrental.api.assembler.ActorModelAssembler;
import com.example.dvdrental.api.representationmodel.ActorModel;
import com.example.dvdrental.entity.Actor;
import com.example.dvdrental.exception.ActorNameNotFoundException;
import com.example.dvdrental.exception.IdNotFoundException;
import com.example.dvdrental.service.ActorService;
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
@RequestMapping("/api/actors")
@Api(tags = {"배우 CRUD API"})
public class ActorController {

    @Autowired
    ActorService actorService;

    @Autowired
    ActorModelAssembler actorModelAssembler;

    @GetMapping(path = "/all")
    @ApiOperation(value = "모든 배우 정보 조회")
    public ResponseEntity<CollectionModel<ActorModel>> retrieveAllActors() {

        final List<Actor> actors = actorService.getAllActors();

        return ResponseEntity.ok(actorModelAssembler.toCollectionModel(actors));
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "id로 배우 정보 조회")
    public ResponseEntity<ActorModel> retrieveActor(@PathVariable int id) {
        return actorService.getActorById(id)
                .map(actorModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new IdNotFoundException(id));
    }

    @GetMapping
    @ApiOperation(value = "이름으로 배우 정보 검색")
    public ResponseEntity<CollectionModel<ActorModel>> findActorByName(@NotNull String name) {

        final List<Actor> actors = actorService.getActorByName(name);

        if(CollectionChecker.isEmpty(actors))
            throw new ActorNameNotFoundException(name);

        return ResponseEntity.ok(actorModelAssembler.toCollectionModel(actors));

    }

    @PostMapping
    @ApiOperation(value = "배우 정보 입력")
    public ResponseEntity<ActorModel> insertNewActor(@RequestBody @Valid ActorDto request) {

        Actor actor = actorService.insertNewActor(request.getFirstName(), request.getLastName());
        ActorModel actorModel = actorModelAssembler.toModel(actor);

        return ResponseEntity.status(HttpStatus.CREATED).body(actorModel);
    }

    @PutMapping(path = "/{id}")
    @ApiOperation(value = "배우 정보 수정")
    public ResponseEntity<ActorModel> updateActor(@PathVariable int id, @RequestBody @Valid ActorDto request) {

        final Actor actor = actorService.getActorById(id)
                            .orElseThrow(()-> new IdNotFoundException(id));

        actorService.updateActor(actor, request.getFirstName(), request.getLastName());

        return ResponseEntity.ok(actorModelAssembler.toModel(actor));
    }

    @DeleteMapping(path = "/{id}")
    @ApiOperation(value = "배우 정보 삭제")
    public ResponseEntity<Void> deleteActor(@PathVariable int id) {

        final Actor actor = actorService.getActorById(id)
                            .orElseThrow(() -> new IdNotFoundException(id));

        actorService.deleteActor(actor);

        return ResponseEntity.noContent().build();
    }


    @Getter
    @Setter
    static class ActorDto {

        @NotNull(message = "first name is required")
        @Size(message = "first name must be lower than 30", min=1, max=30)
        private String firstName;

        @NotNull(message = "last name is required")
        @Size(message = "last name must be lower than 300", min=1, max=30)
        private String lastName;

    }


}
