package com.example.dvdrental.service;

import com.example.dvdrental.entity.Rental;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InventoryServiceImplTest {

    @Autowired
    InventoryService inventoryService;

    @ParameterizedTest
    @CsvSource({"1,4", "2,6", "8,2"})
    void getRentalListById_targetInventory_verifyRentalCount(int targetInventoryId, int expectedCount) {

        List<Rental> result = inventoryService.getRentalListById(targetInventoryId);

        assertEquals(expectedCount, result.size());

    }

}