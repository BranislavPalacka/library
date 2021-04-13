package eu.branislavpalacka.library.mappper;

import eu.branislavpalacka.library.domain.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setAddress(resultSet.getString("address"));
        employee.setEmail(resultSet.getString("email"));
        employee.setPhoneNumber(resultSet.getString("phone_number"));
        employee.setPassword(resultSet.getString("password"));
        employee.setActive(resultSet.getInt("active"));
        return employee;
    }
}
