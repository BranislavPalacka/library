package eu.branislavpalacka.library;

import eu.branislavpalacka.library.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBInsertTest {

    private final String insertUser = "INSERT INTO users (name,surname,address,email,phone_number,password,created_at) VALUES (?,?,?,?,?,?,?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void createUser(){
        User user  = new User();
        user.setName("Petr");
        user.setSurname("Cech");
        user.setAddress("opravdu nevim kde bydli");
        user.setEmail("pert.cech@seznam.cz");
        user.setPhoneNumber("+420 001 002 003");
        user.setPassword("UplneJednoduche123");
        user.setCreatedAt(Timestamp.from(Instant.now()));

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertUser);
                ps.setString(1,user.getName());
                ps.setString(2,user.getSurname());
                ps.setString(3,user.getAddress());
                ps.setString(4,user.getEmail());
                ps.setString(5,user.getPhoneNumber());
                ps.setString(6,user.getPassword());
                ps.setTimestamp(7,user.getCreatedAt());
                return ps;
            }
        });

    }
}
