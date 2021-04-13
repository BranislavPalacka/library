package eu.branislavpalacka.library.DB.services.api;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Employee;
import eu.branislavpalacka.library.domain.Order;
import org.springframework.lang.Nullable;

import java.util.List;

public interface OrderService {
    @Nullable
    Order get(int id);

    @Nullable
    Integer add(Order order);

    List<Order> getOrders();

    void ready(int id);

    void borrowed(Order order, Employee employee);

    void ended(Order order, Employee employee);

    List<Book> booksInOrder(int order_id);
}
