package eu.branislavpalacka.library.DB.mappper;

import eu.branislavpalacka.library.domain.Basket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BasketRowMapper implements RowMapper<Basket> {
    @Override
    public Basket mapRow(ResultSet resultSet, int i) throws SQLException {
        Basket basket = new Basket();
        basket.setId(resultSet.getInt("id"));
        basket.setLastAdd(resultSet.getTimestamp("last_add"));
        return basket;
    }
}
