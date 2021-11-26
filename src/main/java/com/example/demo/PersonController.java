package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@Slf4j
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(value = "/people")
    public List<Person> getPeople(@RequestParam(required = false) Optional<Integer> age) {
        List<Person> all = personService.findAll(age);
        return all;
    }

    @GetMapping(value = "/people/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping(value = "/people")
    public Person createPerson(@Valid @RequestBody Person person) {
        try {
            return personService.savePerson(person);
        } catch (PersonFirstNameTooLongException e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping(value = "/people/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

    @PutMapping(value = "/people/{id}")
    public Person putPerson(@PathVariable Long id, @RequestBody Person personRequest) {
        return personService.update(personRequest, id);
    }

}
