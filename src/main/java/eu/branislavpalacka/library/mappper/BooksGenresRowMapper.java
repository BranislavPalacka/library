package eu.branislavpalacka.library.mappper;

import eu.branislavpalacka.library.domain.BooksGenres;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksGenresRowMapper implements RowMapper<BooksGenres> {

    @Override
    public BooksGenres mapRow(ResultSet resultSet, int i) throws SQLException {
        BooksGenres booksGenres = new BooksGenres(null,null);
        booksGenres.setBookId(resultSet.getInt("book_id"));
        booksGenres.setGenreId(resultSet.getInt("genre_id"));
        return booksGenres;
    }
}
