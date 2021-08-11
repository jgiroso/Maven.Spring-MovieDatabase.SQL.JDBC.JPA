package io.zipcoder.persistenceapp;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Person person = new Person();
            person.setID(resultSet.getInt("id"));
            person.setFIRST_NAME(resultSet.getString("first_name"));
            person.setLAST_NAME(resultSet.getString("last_name"));
            person.setBIRTHDAY(resultSet.getString("birthday"));
            return person;

    }
}
