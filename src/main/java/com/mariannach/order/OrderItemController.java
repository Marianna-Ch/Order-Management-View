package com.mariannach.order;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



@RestController
class OrderItemController {

    private final OrderItemRepository repository;

    private final OrderItemModelAssembler assembler;

    OrderItemController(OrderItemRepository repository, OrderItemModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/products")
    CollectionModel<EntityModel<OrderItem>> all() {

        List<EntityModel<OrderItem>> products = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(products, linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }


    @PostMapping("/products")
    ResponseEntity<?> newProduct(@RequestBody OrderItem newProduct) {

        EntityModel<OrderItem> entityModel = assembler.toModel(repository.save(newProduct));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // Single item


    @GetMapping("/products/{id}")
    EntityModel<OrderItem> one(@PathVariable Long id) {

        OrderItem product = repository.findById(id) //
                .orElseThrow(() -> new ClientNotFoundException(id));

        return assembler.toModel(product);
    }
}