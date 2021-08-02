package com.example.dvdrental.service;

import com.example.dvdrental.entity.Inventory;
import com.example.dvdrental.entity.Rental;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    Optional<Inventory> getInventoryById(int id);

    List<Inventory> getInventoriesByFilmId(int filmId);

    List<Rental> getRentalListById(int id);

    boolean isRentable(Inventory inventory);

}
