package io.zipcoder.persistenceapp.mapper;

import io.zipcoder.persistenceapp.model.Home;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeMapper implements RowMapper {

    @Override
    public Home mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Home home = new Home();
        home.setID(resultSet.getInt("id"));
        home.setADDRESS(resultSet.getString("address"));
        home.setHOMENUMBER(resultSet.getString("homenumber"));
        return home;
    }

}
