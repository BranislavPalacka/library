package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.User;
import eu.branislavpalacka.library.mappper.UserRowMapper;
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
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User get(int id){
        final String sql = "SELECT * FROM user WHERE id="+id;
        try{
            return jdbcTemplate.queryForObject(sql,userRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<User> getAll(){
        final String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql,userRowMapper);
    }

    public Integer add(User user){
        final String sql = "INSER INTO user (name,surname,address,email,phone_number,password,created_at) VALUES (?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,user.getName());
                ps.setString(2, user.getSurname());
                ps.setString(3, user.getAddress());
                ps.setString(4, user.getEmail());
                ps.setString(5, user.getPhoneNumber());
                ps.setString(6, user.getPassword());
                if(user.getCreatedAt() == null){
                    user.setCreatedAt(Timestamp.from(Instant.now()));
                }
                ps.setTimestamp(7,user.getCreatedAt());

                return ps;
            }
        },keyHolder);
        if (keyHolder.getKey() != null){
            return keyHolder.getKey().intValue();
        }else{
            return null;
        }
    }
}
