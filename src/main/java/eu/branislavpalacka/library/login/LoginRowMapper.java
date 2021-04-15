package eu.branislavpalacka.library.login;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRowMapper implements RowMapper<Login> {
    @Override
    public Login mapRow(ResultSet rs, int rowNum) throws SQLException {
        Login login = new Login();
        login.setEmail(rs.getString("email"));
        login.setPassword(rs.getString("password"));
        return login;
    }
}
