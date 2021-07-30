package com.example.dvdrental.api.controller;

import com.example.dvdrental.api.assembler.RentalModelAssembler;
import com.example.dvdrental.api.representationmodel.RentalModel;
import com.example.dvdrental.entity.Rental;
import com.example.dvdrental.exception.IdNotFoundException;
import com.example.dvdrental.service.RentalService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

// 인벤토리를 선택하고 새로운 Rental을 생성 (대여해줌)

@RestController
@RequestMapping(path = "/api/rental")
public class RentalController {

    @Autowired
    RentalService rentalService;

    @Autowired
    RentalModelAssembler rentalModelAssembler;

    @GetMapping(path = "/{rentalId}")
    @ApiOperation(value = "대여 id로 대여 정보 조회")
    public ResponseEntity<RentalModel> retrieveRental(@PathVariable int rentalId) {
        return rentalService.getRentalById(rentalId)
                .map(rentalModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseThrow(()-> new IdNotFoundException(rentalId));
    }

    @PostMapping
    @ApiOperation(value = "새 대여 기록을 작성함")
    public ResponseEntity<RentalDto> createNewRental(@RequestParam Integer inventoryId) {

        Rental rental = rentalService.createNewRental(inventoryId);

        RentalDto rentalDto = new RentalDto();
        rentalDto.setRentalId(rental.getRentalId());
        rentalDto.setInventoryId(rental.getInventoryId());

        return ResponseEntity.status(HttpStatus.CREATED).body(rentalDto);

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static class RentalDto {

        private int rentalId;
        private int inventoryId;
        private Timestamp returnDate;

    }


}
