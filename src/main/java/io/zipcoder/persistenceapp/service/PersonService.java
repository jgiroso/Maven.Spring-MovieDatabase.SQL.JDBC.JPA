package io.zipcoder.persistenceapp.service;

import io.zipcoder.persistenceapp.model.Person;

import java.util.List;
import java.util.Map;

public interface PersonService {

    List<Person> findAll();
    void save(Person person);
    Person findById(Long id);
    long findFirstNameFrequency();
    void deleteById(Long id);
    void update(Long id, Person person);
    List<Person> findAllDistinctLastNames();
    List<Person> findAllByLastName(String LAST_NAME);
}
