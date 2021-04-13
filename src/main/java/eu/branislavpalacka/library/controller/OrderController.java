package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.Order;
import eu.branislavpalacka.library.services.api.EmployeeService;
import eu.branislavpalacka.library.services.api.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;
    private final EmployeeService employeeService;

    public OrderController(OrderService orderService, EmployeeService employeeService) {
        this.orderService = orderService;
        this.employeeService = employeeService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable int id){
        Order order = orderService.get(id);
        if (order !=null){
            return new ResponseEntity<>(order, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Order> orders = orderService.getOrders();
        return new ResponseEntity(orders,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Order order){
        Integer id = orderService.add(order);
        if (id != null){
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("{id}r")
    public ResponseEntity ready(@PathVariable ("id") Integer id){
        if (orderService.get(id) != null){
            orderService.ready(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PatchMapping("{id}b")
    public ResponseEntity borrowed(@PathVariable ("id") Integer order_id, @RequestBody Employee employee){
        if(orderService.get(order_id) != null){
            orderService.borrowed(orderService.get(order_id), employee);
            return new ResponseEntity<>(order_id,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.PRECONDITION_FAILED);
        }
    }

    @PatchMapping("{id}e")
    public ResponseEntity ended(@PathVariable ("id") Integer order_id, @RequestBody Employee employee){
        if(orderService.get(order_id) != null){
            orderService.ended(orderService.get(order_id), employee);
            return new ResponseEntity<>(order_id,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.PRECONDITION_FAILED);
        }
    }

}
