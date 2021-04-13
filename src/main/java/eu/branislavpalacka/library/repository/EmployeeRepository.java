package eu.branislavpalacka.library.repository;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.mappper.EmployeeRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;
    private final EmployeeRowMapper employeeRowMapper = new EmployeeRowMapper();

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Employee get(int id){
        final String sql = "SELECT * FROM employees WHERE id="+id;
        try{
            return jdbcTemplate.queryForObject(sql,employeeRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public List<Employee> getAll(){
        final String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql,employeeRowMapper);
    }

    public Integer add(Employee employee){
        final String sql = "INSERT INTO employees (name,surname,address,email,phone_number,password) VALUES (?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,employee.getName());
                ps.setString(2, employee.getSurname());
                ps.setString(3, employee.getAddress());
                ps.setString(4, employee.getEmail());
                ps.setString(5, employee.getPhoneNumber());
                ps.setString(6, employee.getPassword());
                return ps;
            }
        },keyHolder);
        if (keyHolder.getKey() != null){
            return keyHolder.getKey().intValue();
        }else{
            return null;
        }
    }

    public void deactivate(int id){
        final String sql = "UPDATE employees SET active=0 WHERE id="+id;
        jdbcTemplate.update(sql);
    }

    public void activate(int id){
        final String sql = "UPDATE employees SET active=1 WHERE id="+id;
        jdbcTemplate.update(sql);
    }

    public boolean isActive(int id){
        final String sql = "SELECT * FROM employees WHERE id="+id;
        if (jdbcTemplate.queryForObject(sql,employeeRowMapper).getActive()==1){
            return true;
        }else return false;

    }
}
