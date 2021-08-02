package com.example.dvdrental.service;

import com.example.dvdrental.entity.Inventory;
import com.example.dvdrental.entity.Rental;
import com.example.dvdrental.exception.IdNotFoundException;
import com.example.dvdrental.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public Optional<Inventory> getInventoryById(int id) {
        return inventoryRepository.findById(id);
    }

    @Override
    public List<Rental> getRentalListById(int id) {
        Inventory target = inventoryRepository.findById(id)
                            .orElseThrow(()-> new IdNotFoundException(id));
        return target.getRentalList();
    }

    @Override
    public List<Inventory> getInventoriesByFilmId(int filmId) {

        return inventoryRepository.getInventoriesByFilmId(filmId);
    }

    @Override
    public boolean isRentable(Inventory inventory) {

        List<Rental> rentalList = inventory.getRentalList();

        // Return date null means it hasn't returned
        for(Rental rental : rentalList) {
            if(rental.getReturnDate() == null) return false;
        }

        return true;

    }
}
