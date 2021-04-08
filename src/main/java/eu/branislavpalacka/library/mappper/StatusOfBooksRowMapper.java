package eu.branislavpalacka.library.mappper;


import eu.branislavpalacka.library.domain.StatusOfBooks;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatusOfBooksRowMapper implements RowMapper<StatusOfBooks> {


    @Override
    public StatusOfBooks mapRow(ResultSet resultSet, int i) throws SQLException {
        StatusOfBooks statusOfBooks = new StatusOfBooks();
        statusOfBooks.setId(resultSet.getInt("id"));
        statusOfBooks.setName(resultSet.getString("name"));
        return statusOfBooks;
    }
}
