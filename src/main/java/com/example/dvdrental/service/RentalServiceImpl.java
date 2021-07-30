package com.example.dvdrental.service;

import com.example.dvdrental.entity.Rental;
import com.example.dvdrental.exception.IdNotFoundException;
import com.example.dvdrental.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalRepository rentalRepository;

    @Override
    public Optional<Rental> getRentalById(int id) {
        return rentalRepository.findById(id);
    }

    @Override
    public Rental createNewRental(int inventoryId) {

        Rental rental = new Rental();

        rental.setInventoryId(inventoryId);
        rental.setRentalDate(new Timestamp(System.currentTimeMillis()));

        return rentalRepository.save(rental);
    }


}
