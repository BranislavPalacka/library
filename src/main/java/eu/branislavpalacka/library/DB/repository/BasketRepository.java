package eu.branislavpalacka.library.DB.repository;

import eu.branislavpalacka.library.domain.Basket;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.DB.mappper.BasketRowMapper;
import eu.branislavpalacka.library.DB.mappper.BookRowMapper;
import eu.branislavpalacka.library.DB.services.api.request.UpdateBasketRequest;
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
public class BasketRepository {
    private final JdbcTemplate jdbcTemplate;
    private final BasketRowMapper basketRowMapper = new BasketRowMapper();
    private final BookRowMapper bookRowMapper = new BookRowMapper();

    public BasketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Basket get(int id){
        final String sql = "SELECT * FROM baskets WHERE id="+id;
        try{
            return jdbcTemplate.queryForObject(sql,basketRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Basket> getAll(){
        final String sql = "SELECT * FROM baskets";
        return jdbcTemplate.query(sql,basketRowMapper);
    }

    public Integer add(Basket basket){
        final String sql = "INSERT INTO baskets (last_add) VALUES (?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                if(basket.getLastAdd() == null){
                    basket.setLastAdd(Timestamp.from(Instant.now()));
                }
                ps.setTimestamp(1,basket.getLastAdd());
                return ps;
            }
        },keyHolder);
        if (keyHolder != null){
            return keyHolder.getKey().intValue();
        }else{
            return null;
        }
    }

    public void update(int id, UpdateBasketRequest updateBasketRequest){
        final String sql = "UPDATE baskets SET last_add=? WHERE id="+id;
        jdbcTemplate.update(sql,updateBasketRequest.getLastAdd());
    }

    public void delete(int id){
        final String sql = "DELETE FROM baskets WHERE id="+id;
        jdbcTemplate.update(sql);
    }

    public List<Book> getAllBooksInBasket (int id){
        final String sql = "SELECT * FROM books WHERE basket_id="+id;
        return jdbcTemplate.query(sql,bookRowMapper);
    }

}
