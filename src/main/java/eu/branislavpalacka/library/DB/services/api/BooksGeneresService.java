package eu.branislavpalacka.library.DB.services.api;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Genre;
import org.springframework.lang.Nullable;

import java.util.List;

public interface BooksGeneresService {
    @Nullable
    List<Book> getBooksFromGenre(int genre_id);
    @Nullable
    List<Genre> getGenresForBook(int book_id);


}
