package eu.branislavpalacka.library.mappper;


import eu.branislavpalacka.library.domain.BooksAuthors;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksAuthorsRowMapper implements RowMapper<BooksAuthors> {
    @Override
    public BooksAuthors mapRow(ResultSet resultSet, int i) throws SQLException {
        BooksAuthors booksAuthors = new BooksAuthors(null,null);
        booksAuthors.setBookId(resultSet.getInt("book_id"));
        booksAuthors.setAuthorId(resultSet.getInt("author_id"));
        return booksAuthors;
    }
}
