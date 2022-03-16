package com.example.springdemo1.service;

import com.example.springdemo1.model.api.CreatePersonRequest;
import com.example.springdemo1.model.api.UpdatePerson;
import com.example.springdemo1.model.domain.Person;
import com.example.springdemo1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> fetchPersons(String firstName, String lastName) {
        if (firstName != null && lastName != null) {
            return personRepository.findAllByLastNameAndFirstName(lastName, firstName);
        } else if (firstName != null) {
            return personRepository.findAllByFirstName(firstName);
        } else if (lastName != null) {
            return personRepository.findAllByLastName(lastName);
        }
        return personRepository.findAll();
    }

    public Optional<Person> fetchPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person createPerson(CreatePersonRequest request) {
        Person person = Person.builder()
                .firstName(request.getFirstName())
                .middleName(request.getMiddleName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .build();
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Person updatePerson(Optional<Person> person, UpdatePerson request) {
        Person updatedPerson = Person.builder()
                .id(person.get().getId())
                .firstName(person.get().getFirstName())
                .middleName(person.get().getMiddleName())
                .lastName(person.get().getLastName())
                .email(person.get().getEmail())
                .phone(person.get().getPhone())
                .build();

        if (request.getFirstName() != null) {
            updatedPerson.setFirstName(request.getFirstName());
        }
        if (request.getMiddleName() != null) {
            updatedPerson.setMiddleName(request.getMiddleName());
        }
        if (request.getLastName() != null) {
            updatedPerson.setLastName(request.getLastName());
        }
        if (request.getEmail() != null) {
            updatedPerson.setEmail(request.getEmail());
        }
        if (request.getPhone() != null) {
            updatedPerson.setPhone(request.getPhone());
        }

        return personRepository.save(updatedPerson);
    }
}

