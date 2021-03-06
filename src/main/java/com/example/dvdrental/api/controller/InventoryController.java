package com.example.dvdrental.api.controller;

import com.example.dvdrental.api.assembler.InventoryModelAssembler;
import com.example.dvdrental.api.representationmodel.InventoryModel;
import com.example.dvdrental.entity.Film;
import com.example.dvdrental.exception.IdNotFoundException;
import com.example.dvdrental.service.InventoryService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    InventoryModelAssembler inventoryModelAssembler;

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "인벤토리 id로 정보 조회")
    public ResponseEntity<InventoryModel> retrieveInventory(@PathVariable int id) {
        return inventoryService.getInventoryById(id)
                .map(inventoryModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/film/{filmId}")
    @ApiOperation(value = "영화 id로 Inventory 리스트 불러오기")
    public ResponseEntity<CollectionModel<InventoryModel>> getInventoryIdsByFilmId(@PathVariable int filmId) {

        return ResponseEntity.ok(inventoryModelAssembler.toCollectionModel(inventoryService.getInventoriesByFilmId(filmId)));

    }


}
