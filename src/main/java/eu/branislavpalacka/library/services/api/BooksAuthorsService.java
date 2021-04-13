package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.domain.Book;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BooksAuthorsService {
    @Nullable
    List<Book> getBooksFromAuthor(int author_id);
    @NonNull
    List<Author> getAuthorsOfBook(int book_id);
}
