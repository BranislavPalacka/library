package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.Book;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BookService {
    List<Book> getBooks();

    @Nullable
    Book get(int id);

    @Nullable
    Integer add(Book book);
}
