package com.example.demo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Person {

    private long id;

    @NotBlank
    @Size(max=20)
    private String firstName;

    @NotBlank
    @Size(max=20)
    private String lastName;

    private int age;

    public Person(long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public Person update(Person personRequest) {
        this.firstName = personRequest.getFirstName();
        this.lastName = personRequest.getLastName();
        this.age = personRequest.getAge();
        return this;
    }
}
