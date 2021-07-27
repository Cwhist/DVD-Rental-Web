package com.example.dvdrental.repository;

import com.example.dvdrental.entity.Rental;
import com.example.dvdrental.exception.IdNotFoundException;
import org.apache.tomcat.jni.Time;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RentalRepositoryTest {

    @Autowired
    RentalRepository rentalRepository;

    @ParameterizedTest
    @CsvSource({"2022","2021"})
    @Order(1)
    @DisplayName("생성 테스트")
    public void createRental_verifyRentalIdAndListSize(int inventoryId) {

        List<Rental> listBeforeCreation;
        int sizeBeforeCreation;
        int expectedSize;

        List<Rental> listAfterCreation;
        int sizeAfterCreation;
        Rental target;

        // Before Creation
        listBeforeCreation = rentalRepository.findAll();
        sizeBeforeCreation = listBeforeCreation.size();
        expectedSize = sizeBeforeCreation + 1;

        // Do Create
        Rental newRental = new Rental();
        newRental.setInventoryId(inventoryId);
        newRental.setRentalDate(new Timestamp(System.currentTimeMillis()));

        rentalRepository.save(newRental);

        // After Creation
        listAfterCreation = rentalRepository.findAll();
        target = listAfterCreation.get(listAfterCreation.size()-1);
        sizeAfterCreation = listAfterCreation.size();

        // Assert
        assertEquals(expectedSize, sizeAfterCreation);
        assertEquals(inventoryId, target.getInventoryId());
    }

    @ParameterizedTest
    @CsvSource({"11,2","4,1"})
    @Order(2)
    @DisplayName("수정 테스트")
    public void updateRental_targetRentalId_verifyInventoryIdChanged(int targetRentalId, int newInventoryId) {

        Rental targetAfterUpdate;
        Rental target = (rentalRepository.findById(targetRentalId))
                .orElseThrow(()->new IdNotFoundException(targetRentalId));

        target.setInventoryId(newInventoryId);
        rentalRepository.save(target);

        targetAfterUpdate = rentalRepository.findById(targetRentalId)
                .orElseThrow(()->new IdNotFoundException(targetRentalId));

        assertEquals(targetRentalId, targetAfterUpdate.getRentalId());
        assertEquals(newInventoryId, targetAfterUpdate.getInventoryId());

    }

    @ParameterizedTest
    @CsvSource({"16058", "16059"})
    @Order(3)
    @DisplayName("삭제 테스트")
    public void deleteRental_targetRental_ListSizeDownAndTargetIdNotFound(int targetRentalId) {

        List<Rental> listBeforeDeletion;
        int sizeBeforeDeletion;
        int expectedSize;

        Rental target;

        List<Rental> listAfterDeletion;
        int sizeAfterDeletion;
        Rental targetRentalAfterDeletion;

        // Before Deletion
        listBeforeDeletion = rentalRepository.findAll();
        sizeBeforeDeletion = listBeforeDeletion.size();
        expectedSize = sizeBeforeDeletion - 1;

        // Do Delete
        target = rentalRepository.findById(targetRentalId)
                .orElseThrow(()-> new IdNotFoundException(targetRentalId));
        rentalRepository.delete(target);

        // After Deletion
        listAfterDeletion = rentalRepository.findAll();
        sizeAfterDeletion = listAfterDeletion.size();
        targetRentalAfterDeletion = rentalRepository.findById(targetRentalId)
                .orElse(new Rental(-404, -404, new Timestamp(System.currentTimeMillis()), null));

        // Assert
        assertEquals(expectedSize, sizeAfterDeletion);
        assertEquals(-404, targetRentalAfterDeletion.getRentalId());

    }

    @ParameterizedTest
    @CsvSource({"11541,2026","15894,4416"})
    @Order(4)
    @DisplayName("단일 조회 테스트")
    public void getRental_targetRentalId_verifyInventoryId(int targetRentalId, int expectedInventoryId) {

        Rental target = (rentalRepository.findById(targetRentalId))
                .orElseThrow(()->new IdNotFoundException(targetRentalId));

        assertEquals(expectedInventoryId, target.getInventoryId());

    }

    @ParameterizedTest
    @CsvSource({"16044"})
    @Order(5)
    @DisplayName("전체 조회 테스트 - 리스트 갯수 검사")
    public void getRentals_verifyListSize(int expectedListSize) {

        List<Rental> targetList = rentalRepository.findAll();

        assertEquals(expectedListSize, targetList.size());

    }
}