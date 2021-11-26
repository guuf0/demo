package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class PersonConverter {
    public Person toPerson(PersonEntity personEntity){
        return new Person(personEntity.getId(), personEntity.getFirstName(), personEntity.getLastName(), personEntity.getAge());
    }
    public PersonEntity toPersonEntity(Person person){
        return new PersonEntity(person.getFirstName(), person.getLastName(), person.getAge());
    }
}
