package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Order;
import eu.branislavpalacka.library.services.api.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
}
