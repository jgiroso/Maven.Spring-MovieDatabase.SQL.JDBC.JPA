package io.zipcoder.persistenceapp.service;

import io.zipcoder.persistenceapp.model.Person;
import io.zipcoder.persistenceapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class JpaPersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findOne(id);
    }

    @Override
    public long findFirstNameFrequency() {
        return personRepository.countByFirstName();
    }

    @Override
    public void deleteById(Long id) {
        personRepository.delete(id);
    }

    @Override
    public void update(Long id, Person person) {
        Person retVal = personRepository.findOne(id);
        retVal.setFIRST_NAME(person.getFIRST_NAME());
        retVal.setLAST_NAME(person.getLAST_NAME());
        retVal.setMOBILE(person.getMOBILE());
        retVal.setBIRTHDAY(person.getBIRTHDAY());
        personRepository.save(retVal);
    }

    @Override
    public List<Person> findAllDistinctLastNames() {
        return personRepository.findAllDistinctLastNames();
    }

    @Override
    public List<Person> findAllByLastName(String LAST_NAME) {
        return personRepository.findAllByLastName(LAST_NAME);
    }
}
