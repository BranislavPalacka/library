package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.Employee;
import org.springframework.lang.Nullable;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    @Nullable
    Employee get(int id);

    @Nullable
    Integer add(Employee employee); // returns generated ID
}
