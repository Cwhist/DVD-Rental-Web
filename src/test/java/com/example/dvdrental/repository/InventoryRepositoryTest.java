package com.example.dvdrental.repository;

import com.example.dvdrental.entity.Inventory;
import com.example.dvdrental.exception.IdNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InventoryRepositoryTest {

    @Autowired
    InventoryRepository inventoryRepository;

    @ParameterizedTest
    @CsvSource({"200","201"})
    @Order(1)
    @DisplayName("생성 테스트")
    public void createInventory_verifyFilmIdAndListSize(int expectedFilmId) {

        List<Inventory> listBeforeCreation;
        int sizeBeforeCreation;
        int expectedSize;

        List<Inventory> listAfterCreation;
        int sizeAfterCreation;
        Inventory target;

        // Before Creation
        listBeforeCreation = inventoryRepository.findAll();
        sizeBeforeCreation = listBeforeCreation.size();
        expectedSize = sizeBeforeCreation + 1;

        // Do Create
        Inventory newInventory = new Inventory();
        newInventory.setFilmId(expectedFilmId);
        inventoryRepository.save(newInventory);

        // After Creation
        listAfterCreation = inventoryRepository.findAll();
        target = listAfterCreation.get(listAfterCreation.size()-1);
        sizeAfterCreation = listAfterCreation.size();

        // Assert
        // Verifying Size +1 before creation
        // Verifying filmId
        assertEquals(expectedSize, sizeAfterCreation);
        assertEquals(expectedFilmId, target.getFilmId());
    }

    @ParameterizedTest
    @CsvSource({"11,2","4,1"})
    @Order(2)
    @DisplayName("수정 테스트")
    public void updateInventory_targetInventoryId_verifyFilmIdChanged(int targetInventoryId, int newFilmId) {

        Inventory changedTarget;
        Inventory target = (inventoryRepository.findById(targetInventoryId))
                .orElseThrow(()->new IdNotFoundException(targetInventoryId));

        target.setFilmId(newFilmId);
        inventoryRepository.save(target);

        changedTarget = inventoryRepository.findById(targetInventoryId)
                .orElseThrow(()->new IdNotFoundException(targetInventoryId));

        assertEquals(targetInventoryId, changedTarget.getInventoryId());
        assertEquals(newFilmId, changedTarget.getFilmId());

    }

    @ParameterizedTest
    @CsvSource({"4589", "4588", "4587", "4586", "4585"})
    @Order(3)
    @DisplayName("삭제 테스트")
    public void deleteInventory_targetInventory_ListSizeDownAndTargetIdNotFound(int targetInventoryId) {

        List<Inventory> listBeforeDeletion;
        int sizeBeforeDeletion;
        int expectedSize;

        Inventory targetInventory;

        List<Inventory> listAfterDeletion;
        int sizeAfterDeletion;
        Inventory targetInventoryAfterDeletion;

        // Before Deletion
        listBeforeDeletion = inventoryRepository.findAll();
        sizeBeforeDeletion = listBeforeDeletion.size();
        expectedSize = sizeBeforeDeletion - 1;

        // Do Delete
        targetInventory = inventoryRepository.findById(targetInventoryId)
                .orElseThrow(()-> new IdNotFoundException(targetInventoryId));
        inventoryRepository.delete(targetInventory);

        // After Deletion
        listAfterDeletion = inventoryRepository.findAll();
        sizeAfterDeletion = listAfterDeletion.size();
        targetInventoryAfterDeletion = inventoryRepository.findById(targetInventoryId)
                .orElse(new Inventory(-404, -404));

        // Assert
        assertEquals(expectedSize, sizeAfterDeletion);
        assertEquals(-404, targetInventoryAfterDeletion.getInventoryId());

    }

    @ParameterizedTest
    @CsvSource({"11,2","10,2"})
    @Order(4)
    @DisplayName("단일 조회 테스트")
    public void getInventory_targetInventoryId_verifyFilmId(int targetInventoryId, int expectedFilmId) {

        Inventory target = (inventoryRepository.findById(targetInventoryId))
                      .orElseThrow(()->new IdNotFoundException(targetInventoryId));

        assertEquals(expectedFilmId, target.getFilmId());

    }

    @ParameterizedTest
    @CsvSource({"4581"})
    @Order(5)
    @DisplayName("전체 조회 테스트 - 리스트 갯수 검사")
    public void getInventories_verifyListSize(int expectedListSize) {

        List<Inventory> targetList = inventoryRepository.findAll();

        assertEquals(expectedListSize, targetList.size());

    }


}