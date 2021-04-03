package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.Serie;
import eu.branislavpalacka.library.mappper.SerieRowMapper;
import eu.branislavpalacka.library.services.api.request.UpdateSerieRequest;
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
public class SerieRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SerieRowMapper serieRowMapper = new SerieRowMapper();

    public SerieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Serie get(int id){
        final String sql = "SELECT * FROM series WHERE id="+id;
        try{
            return jdbcTemplate.queryForObject(sql,serieRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Serie> getAll (){
        final String sql = "SELECT * FROM series";
        return jdbcTemplate.query(sql,serieRowMapper );
    }

    public Integer add(Serie serie){
        final String sql = "INSERT INTO series (name) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,serie.getName());
                return ps;
            }
        },keyHolder);
        if (keyHolder != null){
            return keyHolder.getKey().intValue();
        }else {
            return null;
        }
    }

    public void update (int id, UpdateSerieRequest updateSerieRequest){
        final String sql = "UPDATE series SET name=? WHERE id="+id;
        jdbcTemplate.update(sql,updateSerieRequest.getName());
    }

    public void delete(int id){
        final String sql = "DELETE FROM series WHERE id="+id;
        jdbcTemplate.update(sql);
    }
}
