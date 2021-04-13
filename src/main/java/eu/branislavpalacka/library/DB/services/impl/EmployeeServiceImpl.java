package eu.branislavpalacka.library.DB.services.impl;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.DB.repository.EmployeeRepository;
import eu.branislavpalacka.library.DB.services.api.EmployeeService;
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

    @Override
    public void deactivate(int id) {
        employeeRepository.deactivate(id);
    }

    @Override
    public void activate(int id) {
        employeeRepository.activate(id);
    }

    @Override
    public boolean isActive(int id) {
        return employeeRepository.isActive(id);
    }
}
