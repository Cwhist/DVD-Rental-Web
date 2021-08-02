package com.example.dvdrental.api.representationmodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "inventories", itemRelation = "inventory")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class InventoryModel extends RepresentationModel<InventoryModel> {

    private int inventoryId;
    private int filmId;
    private boolean rentable;

}
