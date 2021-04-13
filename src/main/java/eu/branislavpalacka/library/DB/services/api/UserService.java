package eu.branislavpalacka.library.DB.services.api;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.User;
import org.springframework.lang.Nullable;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    @Nullable
    User get(int id);

    @Nullable
    Integer add(User user); // returns generated ID

    List<Book> booksInBasket (int userId);

    Integer numberOfBorrowedBooks(int userId);

    List<Book> borrowedBooks (int user_id);
}
