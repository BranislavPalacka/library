package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Genre;
import eu.branislavpalacka.library.mappper.BookRowMapper;
import eu.branislavpalacka.library.mappper.GenreRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksGenresRepository {
    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper bookRowMapper = new BookRowMapper();
    private final GenreRowMapper genreRowMapper = new GenreRowMapper();

    public BooksGenresRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksFromGenre(int genre_id){
        final String sql = "SELECT * FROM books_genres JOIN books ON book_id = id WHERE genre_id="+genre_id;
        return jdbcTemplate.query(sql,bookRowMapper);
    }

    public List<Genre> getGenresForBook(int book_id){
        final String sql = "SELECT * FROM books_genres JOIN genres ON genre_id = id WHERE book_id="+book_id;
        return jdbcTemplate.query(sql,genreRowMapper);
    }
}
