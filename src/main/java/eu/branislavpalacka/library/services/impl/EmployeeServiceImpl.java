package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.repository.EmployeeRepository;
import eu.branislavpalacka.library.services.api.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.getAll();
    }

    @Override
    public Employee get(int id) {
        return employeeRepository.get(id);
    }

    @Override
    public Integer add(Employee employee) {
        return employeeRepository.add(employee);
    }
}
