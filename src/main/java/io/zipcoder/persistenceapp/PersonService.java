package io.zipcoder.persistenceapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public void save(Person person) {
//        String sql = "insert into person (first_name, last_name, mobile, birthday) " +
//                "values (" + person.getFIRST_NAME() +
//                ", " + person.getLAST_NAME() +
//                ", " + person.getMOBILE() +
//                ", " + person.getBIRTHDAY() +
//                ");";
        jdbcTemplate.update("INSERT INTO person (first_name, last_name, mobile, birthday) VALUES (?, ?, ?, ?)", new Object[] {person.getFIRST_NAME(), person.getLAST_NAME(), person.getMOBILE(), person.getBIRTHDAY()});
        //jdbcTemplate.update(sql, );
    }

    public Person findById(int id) {
        String stringID = String.valueOf(id);
        String sql = "select * from person where id=" + stringID;
        return jdbcTemplate.queryForObject(sql, new PersonMapper());
    }

    public int deleteById(int id) {
        String stringID = String.valueOf(id);
        String sql = "delete from person where id=" + stringID;
        return jdbcTemplate.update(sql, new Object[] { id });
    }

//    public int insert(Person person) {
//        return jdbcTemplate.update("insert into person (id, name, passport_number) " + "values(?,  ?, ?)",
//                new Object[] { person.getID(), person.getFIRST_NAME(), person.getLAST_NAME(), person.getBIRTHDAY() });
//    }
//
    public int update(Person person) {
        return jdbcTemplate.update("update person " + " set first_name = ?, last_name = ?, birthday = ? " + " where id = ?",
                new Object[] { person.getFIRST_NAME(), person.getLAST_NAME(), person.getBIRTHDAY(), person.getID() });
    }

    public List<Person> findAllDistinctLastNames() {
        String sql = "select distinct LAST_NAME from person";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    public List<Person> findAllByLastName(String LAST_NAME) {
        String sql = "select * from person where LAST_NAME=" + LAST_NAME;
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    private Map<String, List<Person>> mapSurnamesToPeople() {
        List<String> distinctLastNames = new ArrayList<>();
        List<Person> lastNames = findAllDistinctLastNames();
        Map<String, List<Person>> retVal = new HashMap<>();

        for(Person person : lastNames) {
            distinctLastNames.add(person.getLAST_NAME());
        }

        for(String lastName : distinctLastNames) {
            retVal.put(lastName, findAllByLastName(lastName));
        }

        return retVal;
    }
}
