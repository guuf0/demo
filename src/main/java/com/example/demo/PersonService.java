package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private PersonConverter personConverter;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonConverter personConverter) {

        this.personRepository = personRepository;
        this.personConverter = personConverter;
    }

    @PostConstruct
    public void init() {
        this.personRepository.deleteAll();

        PersonEntity person1 = new PersonEntity("Jan2", "Kowalski", 22);
        PersonEntity person2 = new PersonEntity("Wiktor", "Kaszuba", 16);
        PersonEntity person3 = new PersonEntity("Adam", "Nowak", 16);

        this.personRepository.save(person1);
        this.personRepository.save(person2);
        this.personRepository.save(person3);
    }

    public List<Person> findAll(Optional<Integer> ageOptional) {
        List<PersonEntity> people = ageOptional
                .map(p -> this.personRepository.findByAge(p))
                .orElse(this.personRepository.findAll());

        return people.stream()
                .map(personConverter::toPerson)
                .toList();
    }

    public Person findById(Long id) {
        Optional<PersonEntity> byId = personRepository.findById(id);
        return  byId
                .map(p -> personConverter.toPerson(p))
                .orElseThrow(() -> new PersonNotFoundException("Person not found by id: " + id));
    }

    public Person savePerson(Person person) throws PersonFirstNameTooLongException {

            PersonEntity savedPersonEntity = personRepository.save(personConverter.toPersonEntity(person));
            return personConverter.toPerson(savedPersonEntity);


    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    public Person update(Person personRequest, Long id) {
        Optional<PersonEntity> person = personRepository.findById(id);

        return person
                .map(p -> personConverter.toPerson(p.update(personRequest)))
                .orElseThrow(() -> new PersonNotFoundException("Person not found by id: " + id));
    }
}
