package com.mariannach.order;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderItemModelAssembler implements RepresentationModelAssembler<OrderItem, EntityModel<OrderItem>> {

    @Override
    public EntityModel<OrderItem> toModel(OrderItem orderItem) {

        return EntityModel.of(orderItem, //
                linkTo(methodOn(ClientController.class).one(orderItem.getId())).withSelfRel(),
                linkTo(methodOn(ClientController.class).all()).withRel("products"));
    }
}
