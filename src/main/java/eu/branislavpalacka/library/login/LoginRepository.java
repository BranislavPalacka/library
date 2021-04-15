package eu.branislavpalacka.library.login;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.User;
import eu.branislavpalacka.library.mappper.EmployeeRowMapper;
import eu.branislavpalacka.library.mappper.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoginRepository {
    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper = new UserRowMapper();
    private final EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();

    public LoginRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserByEmail(String email){
        final String sql = "SELECT * FROM users WHERE email='"+email+"'";
        try{
            return jdbcTemplate.queryForObject(sql,userRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Employee findEmployeeByEmail(String email){
        final String sql = "SELECT * FROM employees WHERE email='"+email+"'";
        try{
            return jdbcTemplate.queryForObject(sql,employeeRowMapper);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }


}
