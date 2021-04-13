package eu.branislavpalacka.library.DB.mappper;

import eu.branislavpalacka.library.domain.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setUserID(resultSet.getInt("user_id"));
        order.setOrderStateId(resultSet.getInt("order_state_id"));
        order.setCreatedAt(resultSet.getTimestamp("created_at"));
        order.setReadyAt(resultSet.getTimestamp("ready_at"));
        order.setBorrowedAt(resultSet.getTimestamp("borrowed_at"));
        order.setEndedAt(resultSet.getTimestamp("ended_at"));
        order.setBorrowedBy(resultSet.getInt("borrowed_by"));
        order.setEndedBy(resultSet.getInt("ended_by"));
        return order;
    }
}
