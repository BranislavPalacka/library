package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.Order;
import eu.branislavpalacka.library.repository.OrderRepository;
import eu.branislavpalacka.library.services.api.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order get(int id) {
        return orderRepository.get(id);
    }

    @Override
    public Integer add(Order order) {
        return orderRepository.add(order);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.getAll();
    }

    @Override
    public void ready(int id) {
        orderRepository.ready(id);

    }

    @Override
    public void borrowed(Order order, Employee employee) {
        orderRepository.borrowed(order, employee);
    }

    @Override
    public void ended(Order order, Employee employee) {
        orderRepository.ended(order, employee);
    }

    @Override
    public List<Book> booksInOrder(int order_id) {
        return orderRepository.booksInOrder(order_id);
    }
}
