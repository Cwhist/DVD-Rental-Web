package com.example.dvdrental.service;

import com.example.dvdrental.entity.Rental;

import java.util.List;

public interface InventoryService {

    List<Rental> getRentalListById(int id);

}
