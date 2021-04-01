package eu.branislavpalacka.library.mappper;

import eu.branislavpalacka.library.domain.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        Author author = new Author();
        author.setId(resultSet.getInt("id"));
        author.setName(resultSet.getString("name"));
        author.setSurname(resultSet.getString("surname"));
        author.setDescription(resultSet.getString("description"));
        author.setImage(resultSet.getString("image"));
        author.setCreatedAt(resultSet.getTimestamp("created_at"));
        author.setAddBy(resultSet.getInt("add_by"));
        return author;
    }
}
