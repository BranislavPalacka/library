package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.mappper.AuthorRowMapper;
import eu.branislavpalacka.library.mappper.BookRowMapper;
import eu.branislavpalacka.library.mappper.BooksAuthorsRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BooksAuthorsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final BooksAuthorsRowMapper booksAuthorsRowMapper = new BooksAuthorsRowMapper();
    private final BookRowMapper bookRowMapper = new BookRowMapper();
    private final AuthorRowMapper authorRowMapper = new AuthorRowMapper();

    public BooksAuthorsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> getBooksFromAuthor(int author_id){
        final String sql = "SELECT * FROM books_authors JOIN books on book_id = id WHERE author_id ="+author_id;
        return jdbcTemplate.query(sql,bookRowMapper);
    }

    public List<Author> getAuthorsOfBook(int book_id){
        final String sql = "SELECT * FROM books_authors JOIN authors on author_id = id WHERE book_id ="+book_id;
        return jdbcTemplate.query(sql,authorRowMapper);
    }
}
