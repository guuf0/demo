package com.example.demo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="people")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private int age;

    protected PersonEntity() {}

    public PersonEntity(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public PersonEntity update(Person personRequest) {
        this.firstName = personRequest.getFirstName();
        this.lastName = personRequest.getLastName();
        this.age = personRequest.getAge();
        return this;
    }


}
