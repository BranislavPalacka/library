package eu.branislavpalacka.library.services.impl;

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
}
