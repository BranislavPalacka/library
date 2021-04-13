package eu.branislavpalacka.library.DB.mappper;

import eu.branislavpalacka.library.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setAddress(resultSet.getString("address"));
        user.setEmail(resultSet.getString("email"));
        user.setPhoneNumber(resultSet.getString("phone_number"));
        user.setPassword(resultSet.getString("password"));
        user.setBorrowedBooksNumber(resultSet.getInt("borrowed_books_number"));
        user.setBasketId(resultSet.getInt("basket_id"));
        user.setAccountApprovedBy(resultSet.getInt("account_approved_by"));
        user.setCreatedAt(resultSet.getTimestamp("created_at"));

        return user;
    }
}
