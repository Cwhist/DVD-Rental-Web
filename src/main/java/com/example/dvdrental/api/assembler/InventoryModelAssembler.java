package com.example.dvdrental.api.assembler;

import com.example.dvdrental.api.controller.InventoryController;
import com.example.dvdrental.api.representationmodel.InventoryModel;
import com.example.dvdrental.entity.Inventory;
import com.example.dvdrental.service.InventoryService;
import com.example.dvdrental.util.CollectionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InventoryModelAssembler extends RepresentationModelAssemblerSupport<Inventory, InventoryModel> {

    @Autowired
    InventoryService inventoryService;

   public InventoryModelAssembler() { super(Inventory.class, InventoryModel.class); }

    @Override
    public InventoryModel toModel(Inventory entity) {

        InventoryModel inventoryModel = instantiateModel(entity);

        inventoryModel.add( linkTo(methodOn(InventoryController.class).retrieveInventory(entity.getInventoryId()))
               .withSelfRel() );

        inventoryModel.setInventoryId(entity.getInventoryId());
        inventoryModel.setFilmId(entity.getFilmId());
        inventoryModel.setRentable(inventoryService.isRentable(entity));

        return inventoryModel;
    }

}
