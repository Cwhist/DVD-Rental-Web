package com.example.dvdrental.api.representationmodel;

import com.example.dvdrental.entity.Rental;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.Column;
import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "rentals", itemRelation = "rental")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RentalModel extends RepresentationModel<RentalModel> {

    private int rentalId;
    private int inventoryId;
    private Timestamp rentalDate;
    private Timestamp returnDate;

}
