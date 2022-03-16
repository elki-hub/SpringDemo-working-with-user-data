package com.example.springdemo1.repository;

import com.example.springdemo1.model.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findAllByLastNameAndFirstName( String lastName, String firstName);
    List<Person> findAllByFirstName(String firstName);
    List<Person> findAllByLastName(String lastName);
}
