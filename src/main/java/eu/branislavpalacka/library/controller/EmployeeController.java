package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.services.api.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Employee employee = employeeService.get(id);
        if(employee == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAll(){
        List<Employee> userList = employeeService.getEmployees();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Employee employee){
        Integer id = employeeService.add(employee);
        if (id != null){
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}d")
    public ResponseEntity deactivate(@PathVariable ("id") int id){
        if(employeeService.get(id) != null){
            employeeService.deactivate(id);
            return ResponseEntity
                    .ok()
                    .build();

        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Employee with id: "+id+" NOT FOUND.");
        }
    }

    @PatchMapping("{id}a")
    public ResponseEntity activate(@PathVariable ("id") int id){
        if(employeeService.get(id) != null){
            employeeService.activate(id);
            return ResponseEntity
                    .ok()
                    .build();

        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Employee with id: "+id+" NOT FOUND.");
        }
    }
}
