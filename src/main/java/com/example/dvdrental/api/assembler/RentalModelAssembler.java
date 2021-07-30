package com.example.dvdrental.api.assembler;

import com.example.dvdrental.api.controller.ActorController;
import com.example.dvdrental.api.controller.RentalController;
import com.example.dvdrental.api.representationmodel.RentalModel;
import com.example.dvdrental.entity.Rental;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RentalModelAssembler extends RepresentationModelAssemblerSupport<Rental, RentalModel> {

    public RentalModelAssembler() { super(RentalController.class, RentalModel.class);}

    @Override
    public RentalModel toModel(Rental entity) {

        RentalModel rentalModel = instantiateModel(entity);

        rentalModel.add( linkTo(methodOn(RentalController.class).retrieveRental(entity.getRentalId()))
                .withSelfRel());

        rentalModel.setRentalId(entity.getRentalId());
        rentalModel.setRentalDate(entity.getRentalDate());
        rentalModel.setInventoryId(entity.getInventoryId());
        rentalModel.setReturnDate(entity.getReturnDate());

        return rentalModel;
    }
}
