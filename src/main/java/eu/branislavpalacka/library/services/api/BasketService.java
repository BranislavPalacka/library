package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.Basket;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.services.api.request.UpdateBasketRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BasketService {
    @Nullable
    Basket get(int id);
    @Nullable
    Integer add(Basket basket);

    List<Basket> getAll();

    void update(int id,UpdateBasketRequest updateBasketRequest);

    String delete(int id);

    List<Book> getAllBooksInBasket(int id);
}
