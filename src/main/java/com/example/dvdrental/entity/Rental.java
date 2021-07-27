package com.example.dvdrental.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int rentalId;

    @Column(name = "inventory_id")
    private int inventoryId;

    @Column(name = "rental_date")
    private Timestamp rentalDate;

    @Column(name = "return_date")
    private Timestamp returnDate;

    // Set customer_id, staff_id in DB Nullable

}
