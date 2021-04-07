package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.mappper.BookRowMapper;
import eu.branislavpalacka.library.services.api.request.UpdateBookRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.Instant;
import java.util.List;

//TODO dodělat metody pro úpravu knihy
@Component
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;
    private final BookRowMapper bookRowMapper = new BookRowMapper();

    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Book get(int id){
        final String sql = "SELECT * FROM books WHERE id="+id;
        try{
            return jdbcTemplate.queryForObject(sql,bookRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Book> getAll(){
        final String sql = "SELECT * FROM books";
        return jdbcTemplate.query(sql,bookRowMapper);
    }

    public Integer add(Book book){
        final String sql = "INSERT INTO books (name,description,image,status_id,add_by,created_at)"+
                            "VALUES (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,book.getName());
                ps.setString(2,book.getDescription());
                ps.setString(3,book.getImage());
                ps.setInt(4,book.getStatusID());
                ps.setInt(5,book.getAddBy());
                if(book.getCreatedAt() == null){
                    book.setCreatedAt(Timestamp.from(Instant.now()));
                }
                ps.setTimestamp(6,book.getCreatedAt());

                return ps;
            }
        },keyHolder);
        if (keyHolder.getKey() != null){
            return keyHolder.getKey().intValue();
        }else{
            return null;
        }
    }

    public void update(int id, UpdateBookRequest updateBookRequest){
        final String sql = "UPDATE books SET name=?,description=?,image=?,status_id=?,basket_id=? WHERE id="+id;
        jdbcTemplate.update(sql,updateBookRequest.getName(),updateBookRequest.getDescription(),updateBookRequest.getImage(),updateBookRequest.getStatusID(),updateBookRequest.getBasketId());
    }

    public void delete(int id){
        final String sql = "DELETE FROM books WHERE id="+id;
        jdbcTemplate.update(sql);
    }
}
