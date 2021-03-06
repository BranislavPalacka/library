package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.mappper.AuthorRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.Instant;
import java.util.List;

@Component
public class AuthorRepository {
    private final JdbcTemplate jdbcTemplate;
    private final AuthorRowMapper authorRowMapper = new AuthorRowMapper();

    public AuthorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Author get(int id){
        final String sql = "SELECT * FROM authors WHERE id="+id;

        try {
            return jdbcTemplate.queryForObject(sql,authorRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Author> getAll(){
        final String sql = "SELECT * FROM authors";
        return jdbcTemplate.query(sql,authorRowMapper);
    }

    public Integer add(Author author){
        final String sql = "INSERT INTO authors (name,surname,description,image,created_at,add_by) VALUES (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, author.getName());
                ps.setString(2, author.getSurname());
                ps.setString(3, author.getDescription());
                ps.setString(4, author.getImage());
                if(author.getCreatedAt() == null){
                    author.setCreatedAt(Timestamp.from(Instant.now()));
                }
                ps.setTimestamp(5,author.getCreatedAt());
                ps.setInt(6,author.getAddBy());
                return ps;
            }
        },keyHolder);
        if (keyHolder != null) {
            return keyHolder.getKey().intValue();
        }else{
            return null;
        }
    }
}
