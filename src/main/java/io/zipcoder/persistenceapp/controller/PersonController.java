package io.zipcoder.persistenceapp.controller;

import io.zipcoder.persistenceapp.model.Person;
import io.zipcoder.persistenceapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping("/create")
    public ResponseEntity<?> createPerson(@RequestBody Person person) {
        this.personService.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getAllPeople() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonByID(@PathVariable Long id) {
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/people/surname")
    public ResponseEntity<Iterable<Person>> getSurnames() {
        return new ResponseEntity<>(personService.findAllDistinctLastNames(), HttpStatus.OK);
    }

    @GetMapping("/people/surname/{lastName}")
    public ResponseEntity<Iterable<Person>> findAllByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(personService.findAllByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping("/people/firstname/stats")
    public ResponseEntity<Long> findNumberOfFirstNameOccurrances() {
        return new ResponseEntity<>(personService.findFirstNameFrequency(), HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        this.personService.update(id, person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/people/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id) {
        personService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
