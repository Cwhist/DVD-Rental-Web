package com.example.dvdrental.service;

import com.example.dvdrental.entity.Rental;

import java.util.Optional;

public interface RentalService {

    public Optional<Rental> getRentalById(int id);

    public Rental createNewRental(int inventoryId);

}
