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

    public PersonService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    public void save(Person person) {
        String sql = "insert into person (FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY) " +
                "values ('" + person.getFIRST_NAME() +
                "', '" + person.getLAST_NAME() +
                "', '" + person.getMOBILE() +
                "', " + person.getBIRTHDAY() +
                ");";
        jdbcTemplate.update(sql);
    }

    public Person findById(int id) {
        String stringID = String.valueOf(id);
        String sql = "select * from person where id=" + stringID;
        return jdbcTemplate.queryForObject(sql, new PersonMapper());
    }

    public Integer findFirstNameFrequency(){
        String sql = "select FIRST_NAME, count(FIRST_NAME) from person group by FIRST_NAME";
        List<Person> firstNameList = jdbcTemplate.query(sql, new PersonMapper());
        return firstNameList.size();
    }

    public int deleteById(int id) {
        String stringID = String.valueOf(id);
        String sql = "delete from person where id=" + stringID;
        return jdbcTemplate.update(sql, new Object[] { id });
    }

    public int update(int id, Person person) {
        String sql = "UPDATE PERSON" +
                " SET FIRST_NAME = '" + person.getFIRST_NAME() +
                "', LAST_NAME = '" + person.getLAST_NAME() +
                "', MOBILE = '" + person.getMOBILE() +
                "', BIRTHDAY = " + person.getBIRTHDAY() +
                " WHERE ID = " + id +
                ";";
        return jdbcTemplate.update(sql);
    }

    public List<Person> findAllDistinctLastNames() {
        String sql = "select distinct LAST_NAME from person";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    public List<Person> findAllByLastName(String LAST_NAME) {
        String sql = "select * from person where LAST_NAME = '" + LAST_NAME + "'";
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
