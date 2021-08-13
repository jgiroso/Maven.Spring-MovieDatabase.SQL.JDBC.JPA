package io.zipcoder.persistenceapp.JdbcService;

import io.zipcoder.persistenceapp.mapper.PersonMapper;
import io.zipcoder.persistenceapp.model.Person;
import io.zipcoder.persistenceapp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JdbcPersonServiceImpl implements PersonService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public JdbcPersonServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("select * from person", new PersonMapper());
    }

    @Override
    public void save(Person person) {
//        String sql = "insert into person (FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY) " +
//                "values ('" + person.getFIRST_NAME() +
//                "', '" + person.getLAST_NAME() +
//                "', '" + person.getMOBILE() +
//                "', " + person.getBIRTHDAY() +
//                ");";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String sql = "insert into person(FIRST_NAME, LAST_NAME, MOBILE, BIRTHDAY) values(?,?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql);
//                ps.setInt(1, person.getID());
                ps.setString(2, person.getFIRST_NAME());
                ps.setString(3, person.getLAST_NAME());
                ps.setString(4, person.getMOBILE());
                ps.setDate(5, person.getBIRTHDAY());
//                ps.setInt(6, person.getHOME_ID());
                return ps;
            }
        };

        jdbcTemplate.update(psc);
    }
    //("UPDATE tbl_employees SET name = ?, location = ?, department = ? WHERE id = ?", new Object[] {e.getName(), e.getLocation(), e.getDepartment(), id})
    @Override
    public Person findById(Long id) {
        String stringID = String.valueOf(id);
        String sql = "select * from person where id=" + stringID;
        return jdbcTemplate.queryForObject(sql, new PersonMapper());
    }

    @Override
    public long findFirstNameFrequency(){
        String sql = "select FIRST_NAME, count(FIRST_NAME) from person group by FIRST_NAME";
        List<Person> firstNameList = jdbcTemplate.query(sql, new PersonMapper());
        return firstNameList.size();
    }

    @Override
    public void deleteById(Long id) {
        String stringID = String.valueOf(id);
        String sql = "delete from person where id= ?";
        Person person = findById(id);
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void update(Long id, Person person) {
        String sql = "UPDATE PERSON" +
                " SET FIRST_NAME = '" + person.getFIRST_NAME() +
                "', LAST_NAME = '" + person.getLAST_NAME() +
                "', MOBILE = '" + person.getMOBILE() +
                "', BIRTHDAY = " + person.getBIRTHDAY() +
                " WHERE ID = " + id +
                ";";
        jdbcTemplate.update(sql);
    }

    @Override
    public List<Person> findAllDistinctLastNames() {
        String sql = "select distinct LAST_NAME from person";
        return jdbcTemplate.query(sql, new PersonMapper());
    }

    @Override
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
