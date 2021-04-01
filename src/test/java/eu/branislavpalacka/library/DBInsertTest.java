package eu.branislavpalacka.library;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Employee;
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
    private final String insertEmployee = "INSERT INTO employees (name,surname,address,email,phone_number,password) VALUES (?,?,?,?,?,?)";
    private final String insertBook = "INSERT INTO books (name,description,image,status_id,add_by,created_at,basket_id) VALUES (?,?,?,?,?,?,?)";

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

    @Test
    public void createEmployee(){
        Employee employee  = new Employee();
        employee.setName("Tomáš");
        employee.setSurname("Nekonečný");
        employee.setAddress("u Šumu svistu");
        employee.setEmail("tom.tom@seznam.cz");
        employee.setPhoneNumber("+420 758 425 003");
        employee.setPassword("SumSvistu98");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertEmployee);
                ps.setString(1,employee.getName());
                ps.setString(2,employee.getSurname());
                ps.setString(3,employee.getAddress());
                ps.setString(4,employee.getEmail());
                ps.setString(5,employee.getPhoneNumber());
                ps.setString(6,employee.getPassword());
                return ps;
            }
        });

    }

    @Test
    public void createBook(){
        Book book = new Book();
        book.setName("Dobrý voják Švejk");
        book.setDescription("Úplně nejlepší kniha ever.");
        book.setImage("obrazky/svejk.jpg");
        book.setStatusID(1);
        book.setAddBy(2);
        book.setCreatedAt(Timestamp.from(Instant.now()));
        book.setBasketID(13);

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(insertBook);
                ps.setString(1,book.getName());
                ps.setString(2,book.getDescription());
                ps.setString(3,book.getImage());
                ps.setInt(4,book.getStatusID());
                ps.setInt(5,book.getStatusID());
                ps.setTimestamp(6,book.getCreatedAt());
                ps.setInt(7,book.getBasketID());
                return ps;
            }
        });
    }
}
