package io.zipcoder.persistenceapp;

import com.sun.deploy.net.HttpResponse;
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
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        personService.save(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/people")
    public ResponseEntity<Iterable<Person>> getAllPeople() {
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonByID(@PathVariable int id) {
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/people/surname/{lastName}")
    public ResponseEntity<Iterable<Person>> findAllByLastName(@PathVariable String lastName) {
        return new ResponseEntity<>(personService.findAllByLastName(lastName), HttpStatus.OK);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id, @RequestBody Person person) {
        personService.update(id, person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
