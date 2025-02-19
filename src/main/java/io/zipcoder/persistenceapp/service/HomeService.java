package io.zipcoder.persistenceapp.service;

import io.zipcoder.persistenceapp.mapper.HomeMapper;
import io.zipcoder.persistenceapp.mapper.PersonMapper;
import io.zipcoder.persistenceapp.model.Home;
import io.zipcoder.persistenceapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public HomeService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Home home) {
        String sql = "insert into home (ADDRESS, HOMENUMBER" +
                "values ('" + home.getADDRESS() +
                "', '" + home.getHOMENUMBER() +
                "');";
        jdbcTemplate.update(sql);
    }

    public int update(Integer id, Home home) {
        String sql = "update home" +
                " set ADDRESS = '" + home.getADDRESS() +
                "', HOMENUMBER = '" + home.getHOMENUMBER() +
                "' where ID = " + id +
                ";";
        return jdbcTemplate.update(sql);
    }

    public Home deleteById(int id) {
        String stringID = String.valueOf(id);
        Home home = findById(id);
        String sql = "delete from home where id = ?";
        jdbcTemplate.update(sql, id);
        return home;
    }

    public List<Home> deleteListOfHomes(List<Home> homes) {
        String stringHomes = "";

        StringBuilder sb = new StringBuilder();
        for(Home home : homes) {
            sb.append(home);
            sb.append(",");
        }
        stringHomes = sb.toString().trim();
        String sql = "delete from home where id in (" + stringHomes + ");";

        jdbcTemplate.update(sql, new HomeMapper());
        return homes;
    }

    public List<Home> findAll() {
        return jdbcTemplate.query("select * from home", new HomeMapper());
    }

    public Home findById(int id) {
        String stringID = String.valueOf(id);
        String sql = "select * from home where id = " + stringID;
        return (Home) jdbcTemplate.queryForObject(sql, new HomeMapper());
    }

    public Home findByAddress(String address) {
        String sql = "select * from home where address = " + address;
        return (Home) jdbcTemplate.queryForObject(sql, new HomeMapper());
    }

    public Home findByHomeNumber(String homeNumber) {
        String sql = "select * from home where homenumber = " + homeNumber;
        return (Home) jdbcTemplate.queryForObject(sql, new HomeMapper());
    }

    public Home findByPersonId(Person person) {
        String id = String.valueOf(person.getID());
        String sql = "select * from home where id = " + id;
        return (Home) jdbcTemplate.queryForObject(sql, new HomeMapper());
    }

    public List<Person> findPeopleByHome(int id) {
        String sql = "select * from person where home_id = " + id;
        return jdbcTemplate.query(sql, new PersonMapper());
    }
}
