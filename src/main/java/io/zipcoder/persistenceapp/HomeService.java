package io.zipcoder.persistenceapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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

    public int deleteById(int id) {
        String stringID = String.valueOf(id);
        String sql = "delete from home where id = " + stringID;
        return jdbcTemplate.update(sql, new Object[] { id });
    }
}
