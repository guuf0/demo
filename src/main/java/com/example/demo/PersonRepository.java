package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    List<PersonEntity> findByAge(int age);

    PersonEntity findById(long id);
}
