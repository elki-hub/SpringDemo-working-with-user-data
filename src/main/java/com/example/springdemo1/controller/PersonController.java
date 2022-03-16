package com.example.springdemo1.controller;

import com.example.springdemo1.model.api.CreatePersonRequest;
import com.example.springdemo1.model.api.PersonResponse;
import com.example.springdemo1.model.api.UpdatePerson;
import com.example.springdemo1.model.domain.Person;
import com.example.springdemo1.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/persons")
@Tag(name = "Person Controller", description = "Experience service to fetch data")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @Operation(summary = "Fetch persons data from Data Base")
    @ApiResponse(
            responseCode = "200",
            description = "A list of person objects",
            content = @Content(schema = @Schema(implementation = PersonResponse.class)))
    public List<PersonResponse> fetchPersons(@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName) {
        return personService.fetchPersons(firstName, lastName)
                .stream()//vietoj for atspauzdina visus parametrus
                .map(p -> new PersonResponse(p.getFirstName(), p.getLastName(), p.getEmail(), p.getPhone()))
                .collect(Collectors.toList());
    }

    @PostMapping()
    @Operation(summary = "Add new person in database")
    @ApiResponse(
            responseCode = "200",
            description = "A person objects",
            content = @Content(schema = @Schema(implementation = Long.class)))
    public Long createPerson(@Validated @RequestBody CreatePersonRequest request) {
        return personService.createPerson(request).getId();
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete person")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
//        personService.deletePerson(id);
//        return ResponseEntity.noContent().build();
//    }
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Update person info from database using id")
    @ApiResponse(
            responseCode = "200",
            description = "A person objects",
            content = @Content(schema = @Schema(implementation = UpdatePerson.class)))
    public UpdatePerson updatePerson(@PathVariable Long id, @Validated @RequestBody(required = false) UpdatePerson request) {
        Optional<Person> person = personService.fetchPersonById(id);
        if (person.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person not found with the required id");
        }

        Person updatedPerson = personService.updatePerson(person, request);
        return new UpdatePerson(updatedPerson.getFirstName(), updatedPerson.getMiddleName(), updatedPerson.getLastName(), updatedPerson.getEmail(), updatedPerson.getPhone());
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "Fetch person data from database using id")
    @ApiResponse(
            responseCode = "200",
            description = "A person objects",
            content = @Content(schema = @Schema(implementation = PersonResponse.class)))
    public PersonResponse fetchPersonById(@PathVariable Long id) {
        Optional<Person> person = personService.fetchPersonById(id);
        if (person.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Person not found with the required id");
        }
        return new PersonResponse(person.get().getFirstName(), person.get().getLastName(), person.get().getEmail(), person.get().getPhone());
    }
}
