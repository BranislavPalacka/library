package eu.branislavpalacka.library.mappper;

import eu.branislavpalacka.library.domain.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setName(resultSet.getString("name"));
        book.setDescription(resultSet.getString("description"));
        book.setImage(resultSet.getString("image"));
        book.setStatusID(resultSet.getInt("status_id"));
        book.setAddBy(resultSet.getInt("add_by"));
        book.setCreatedAt(resultSet.getTimestamp("created_at"));
        book.setBasketID(resultSet.getInt("basket_id"));

        return book;
    }
}
