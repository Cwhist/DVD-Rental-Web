package com.example.dvdrental.api.controller;

import com.example.dvdrental.service.InventoryService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/rentable/{id}")
    @ApiOperation(value = "인벤토리 id로 대여 가능 여부 확인")
    public ResponseEntity<InventoryRentableDto> checkRentableByInventoryId(@PathVariable int id) {

        boolean result = inventoryService.checkRentableById(id);

        InventoryRentableDto rentableDto = new InventoryRentableDto(id, result);

        return ResponseEntity.ok(rentableDto);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class InventoryRentableDto {

        private int inventoryId;
        private boolean rentable;

    }


}
