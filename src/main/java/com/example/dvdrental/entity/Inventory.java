package com.example.dvdrental.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private int inventoryId;

    @Column(name = "film_id")
    private int filmId;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "inventory_id")
    private List<Rental> rentalList;

    // Set store_id in DB Nullable

}
