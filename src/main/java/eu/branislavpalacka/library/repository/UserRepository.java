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
        final String sql = "SELECT * FROM users WHERE id="+id;
        try{
            return jdbcTemplate.queryForObject(sql,userRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<User> getAll(){
        final String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql,userRowMapper);
    }

    public Integer add(User user){
        final String sql = "INSERT INTO users (name,surname,address,email,phone_number,password,borrowed_books_number,basket_id,account_approved_by,created_at) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
                ps.setInt(7,user.getBorrowedBooksNumber());
                ps.setInt(8,user.getBasketId());
                ps.setInt(9,user.getAccountApprovedBy());

                if(user.getCreatedAt() == null){
                    user.setCreatedAt(Timestamp.from(Instant.now()));
                }
                ps.setTimestamp(10,user.getCreatedAt());

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
