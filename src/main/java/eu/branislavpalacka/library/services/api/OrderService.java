package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.Order;
import org.springframework.lang.Nullable;

import java.util.List;

public interface OrderService {
    @Nullable
    Order get(int id);

    @Nullable
    Integer add(Order order);

    List<Order> getOrders();
}
