package io.zipcoder.persistenceapp.controller;

import io.zipcoder.persistenceapp.model.Home;
import io.zipcoder.persistenceapp.service.HomeService;
import io.zipcoder.persistenceapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    HomeService homeService;

    @PostMapping("/create")
    public ResponseEntity<Home> createHome(@RequestBody Home home) {
        homeService.save(home);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/homes")
    public ResponseEntity<Iterable<Home>> getAllHomes() {
        return new ResponseEntity<>(homeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/homes/{id}")
    public ResponseEntity<Home> getHomeByID(@PathVariable int id) {
        return new ResponseEntity<>(homeService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/homes/address")
    public ResponseEntity<Home> getHomeByAddress(@RequestBody String address) {
        return new ResponseEntity<>(homeService.findByAddress(address), HttpStatus.OK);
    }

    @GetMapping("/homes/homenumber")
    public ResponseEntity<Home> getHomeByHomeNumber(@RequestBody String homeNumber) {
        return new ResponseEntity<>(homeService.findByHomeNumber(homeNumber), HttpStatus.OK);
    }

    @GetMapping("/homes/personid")
    public ResponseEntity<Home> getHomeByPersonID(@RequestBody Person person) {
        return new ResponseEntity<>(homeService.findByPersonId(person), HttpStatus.OK);
    }

    @GetMapping("/homes/people/{id}")
    public ResponseEntity<List<Person>> getPeopleByHome(@PathVariable int id) {
        return new ResponseEntity<>(homeService.findPeopleByHome(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Integer> updateHome(@PathVariable int id, @RequestBody Home home) {
        return new ResponseEntity<>(homeService.update(id, home), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Home> deleteById(@PathVariable int id) {
        return new ResponseEntity<>(homeService.deleteById(id), HttpStatus.OK);
    }

    @DeleteMapping("/homes/delete")
    public ResponseEntity<List<Home>> deleteListOfHomes(@RequestBody List<Home> homes) {
        return new ResponseEntity<>(homeService.deleteListOfHomes(homes), HttpStatus.OK);
    }
}
