package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.services.api.request.UpdateBookRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    @Nullable
    Book get(int id);

    @Nullable
    Integer add(Book book);

    void update (int id, UpdateBookRequest updateBookRequest);

    void delete(int id);

    void addBookToOrder(int book_id,int order_id);

    void addBookToBasket(int book_id,int basket_id);
}
