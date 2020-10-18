package com.mariannach.order;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
class ClientController {

    private final ClientRepository repository;

    private final ClientModelAssembler assembler;

    ClientController(ClientRepository repository, ClientModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/employees")
    CollectionModel<EntityModel<Client>> all() {

        List<EntityModel<Client>> employees = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(ClientController.class).all()).withSelfRel());
    }


    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Client newClient) {

        EntityModel<Client> entityModel = assembler.toModel(repository.save(newClient));

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    // Single item


    @GetMapping("/employees/{id}")
    EntityModel<Client> one(@PathVariable Long id) {

        Client client = repository.findById(id) //
                .orElseThrow(() -> new ClientNotFoundException(id));

        return assembler.toModel(client);
    }


    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Client newClient, @PathVariable Long id) {

        Client updatedClient = repository.findById(id) //
                .map(employee -> {
                    employee.setName(newClient.getName());
                    employee.setRole(newClient.getRole());
                    return repository.save(employee);
                }) //
                .orElseGet(() -> {
                    newClient.setId(id);
                    return repository.save(newClient);
                });

        EntityModel<Client> entityModel = assembler.toModel(updatedClient);

        return ResponseEntity //
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
                .body(entityModel);
    }

    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
