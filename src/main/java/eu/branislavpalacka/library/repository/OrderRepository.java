package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.Order;
import eu.branislavpalacka.library.mappper.BookRowMapper;
import eu.branislavpalacka.library.mappper.OrderRowMapper;
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
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    private final OrderRowMapper orderRowMapper = new OrderRowMapper();
    private final BookRowMapper bookRowMapper = new BookRowMapper();

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Order get(int id){
        final String sql = "SELECT * FROM orders WHERE id="+id;
        try{
            return jdbcTemplate.queryForObject(sql,orderRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Order> getAll(){
        final String sql = "SELECT * FROM orders";
        return jdbcTemplate.query(sql,orderRowMapper);
    }

    public Integer add(Order order){
        final String sql = "INSERT INTO orders (user_id,created_at) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Integer id = jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,order.getUserID());
                if (order.getCreatedAt() == null){
                    order.setCreatedAt(Timestamp.from(Instant.now()));
                }
                ps.setTimestamp(2,order.getCreatedAt());
                return ps;
            }
        },keyHolder);
        if (keyHolder != null){
            return keyHolder.getKey().intValue();
        }else{
            return null;
        }
    }

    // TODO zajisti aby bylo možné ready i rušit a přepisovat kvůli chybám na straně employee
    public void ready(int id){
        final String sql = "UPDATE orders SET ready_at=? WHERE id="+id;
        Order order = get(id);

        if (order.getReadyAt() == null){
            order.setReadyAt(Timestamp.from(Instant.now()));
        }

        jdbcTemplate.update(sql, order.getReadyAt());

    }

    public void borrowed(Order order, Employee employee){
        final String sql = "UPDATE orders SET borrowed_at=?,borrowed_by=? WHERE id="+order.getId();

            if(order.getBorrowedAt() == null){
                order.setBorrowedAt(Timestamp.from(Instant.now()));
            }
            jdbcTemplate.update(sql,order.getBorrowedAt(),employee.getId());
    }

    public void ended(Order order, Employee employee){
        final String sql = "UPDATE orders SET ended_at=?,ended_by=? WHERE id="+order.getId();

        if(order.getEndedAt() == null){
            order.setEndedAt(Timestamp.from(Instant.now()));
        }
        jdbcTemplate.update(sql,order.getEndedAt(),employee.getId());
    }

    public List<Book> booksInOrder(int order_id){
        final String sql= "SELECT books.* FROM orders JOIN books ON orders.id = books.order_id WHERE orders.id = "+order_id;
        return jdbcTemplate.query(sql,bookRowMapper);
    }

}
