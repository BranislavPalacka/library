package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.StatusOfBooks;
import eu.branislavpalacka.library.mappper.StatusOfBooksRowMapper;
import eu.branislavpalacka.library.services.api.request.UpdateStatusOfBooksRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class StatusOfBooksRepository {
    private final JdbcTemplate jdbcTemplate;
    private final StatusOfBooksRowMapper statusOfBooksRowMapper = new StatusOfBooksRowMapper();

    public StatusOfBooksRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public StatusOfBooks get(int id) {
        final String sql = "SELECT * FROM status_of_books WHERE id=" + id;
        try {
            return jdbcTemplate.queryForObject(sql,statusOfBooksRowMapper);
        }catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<StatusOfBooks> getAll(){
        final String sql = "SELECT * FROM status_of_books";
        return jdbcTemplate.query(sql,statusOfBooksRowMapper);
    }

    public Integer add(StatusOfBooks statusOfBooks){
        final String sql = "INSERT INTO status_of_books (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, statusOfBooks.getName());
                return ps;
            }
        },keyHolder);
        if (keyHolder != null){
            return keyHolder.getKey().intValue();
        }else{
            return null;
        }
    }

    public void update (int id, UpdateStatusOfBooksRequest updateStatusOfBooksRequest){
        final String sql = "UPDATE status_of_books SET name=? WHERE id="+id;
        jdbcTemplate.update(sql,updateStatusOfBooksRequest.getName());
    }

    public void delete (int id){
        final String sql = "DELETE FROM status_of_books WHERE id="+id;
        jdbcTemplate.update(sql);
    }
}
