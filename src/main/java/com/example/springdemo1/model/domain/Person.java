package com.example.springdemo1.model.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "PERSON") //to get data from db table Person
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id //pagal sita lauka bus identifikuojamas objektas
    @Column(name="PID", nullable= false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //automatiskai priskiria id
    private Long id;

    @Column(name="FIRST_NAME")
    private String firstName;

    @Column(name="MIDDLE_NAME")
    private String middleName;

    @Column(name="LAST_NAME")
    private String lastName;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PHONE")
    private String phone;
}
