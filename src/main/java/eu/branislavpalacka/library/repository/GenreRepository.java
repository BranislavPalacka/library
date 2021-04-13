package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.BooksGenres;
import eu.branislavpalacka.library.domain.Genre;
import eu.branislavpalacka.library.mappper.BooksGenresRowMapper;
import eu.branislavpalacka.library.mappper.GenreRowMapper;
import eu.branislavpalacka.library.services.api.request.UpdateGenreRequest;
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
public class GenreRepository {
    private final JdbcTemplate jdbcTemplate;
    private final GenreRowMapper genreRowMapper = new GenreRowMapper();
    private final BooksGenresRowMapper booksGenresRowMapper = new BooksGenresRowMapper();

    public GenreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Genre get(int id){
        final String sql = "SELECT * FROM genres WHERE id="+id;
        try {
            return jdbcTemplate.queryForObject(sql,genreRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Genre> getAll(){
        final String sql = "SELECT * FROM genres";
        return jdbcTemplate.query(sql,genreRowMapper);
    }

    public Integer add(Genre genre){
        final String sql = "INSERT INTO genres (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,genre.getName());
                return ps;
            }
        },keyHolder);
        if (keyHolder != null){
            return keyHolder.getKey().intValue();
        }else {
            return null;
        }
    }

    public void update(int id, UpdateGenreRequest updateGenreRequest){
        final String sql = "UPDATE genres SET name=? WHERE id="+id;
        jdbcTemplate.update(sql,updateGenreRequest.getName());
    }

    public void delete (int id){
        final String sql = "DELETE FROM genres WHERE id="+id;
        jdbcTemplate.update(sql);
    }

    public boolean isUsed(int genre_id){
        final String sql = "SELECT * FROM books_genres WHERE genre_id="+genre_id;
        List<BooksGenres> booksGenresList = jdbcTemplate.query(sql,booksGenresRowMapper);
        if (booksGenresList.size() == 0){
            return false;
        }else return true;
    }
}
