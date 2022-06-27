package com.example.dvdrental.api.controller;

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

    @PostMapping
    @ApiOperation(value = "새 대여 기록을 작성함")
    public ResponseEntity<RentalDto> createNewRental(@RequestBody RentalDto dto) {

        Rental rental = rentalService.createNewRental(dto.getInventoryId());

        RentalDto rentalDto = new RentalDto();
        rentalDto.setRentalId(rental.getRentalId());
        rentalDto.setInventoryId(rental.getInventoryId());

        return ResponseEntity.status(HttpStatus.CREATED).body(rentalDto);

    }

    @PostMapping(path = "/return")
    @ApiOperation(value = "rentalId로 반납")
    public ResponseEntity<RentalDto> returnByRentalId(@RequestBody RentalDto dto) {

        if(!rentalService.isReturned(dto.rentalId)) {
            Rental rental = rentalService.returnFilmByRentalId(dto.rentalId);
            dto.setInventoryId(rental.getInventoryId());
            dto.setReturnDate(rental.getReturnDate());

            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

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
