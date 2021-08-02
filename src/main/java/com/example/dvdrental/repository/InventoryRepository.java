package com.example.dvdrental.repository;

import com.example.dvdrental.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> getInventoriesByFilmId(int filmId);
}
