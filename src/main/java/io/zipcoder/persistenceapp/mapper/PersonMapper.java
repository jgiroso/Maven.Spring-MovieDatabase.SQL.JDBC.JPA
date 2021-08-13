package io.zipcoder.persistenceapp.mapper;

import io.zipcoder.persistenceapp.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

        @Override
        public Person mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Person person = new Person();
            person.setID(resultSet.getLong("id"));
            person.setFIRST_NAME(resultSet.getString("first_name"));
            person.setLAST_NAME(resultSet.getString("last_name"));
            person.setBIRTHDAY(resultSet.getDate("birthday"));
            return person;

    }
}
