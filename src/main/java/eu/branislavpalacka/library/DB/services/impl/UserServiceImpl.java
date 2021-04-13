package eu.branislavpalacka.library.DB.services.impl;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.User;
import eu.branislavpalacka.library.DB.repository.UserRepository;
import eu.branislavpalacka.library.DB.services.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(int id) {
        return userRepository.get(id);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.getAll();
    }

    @Override
    public Integer add(User user) {
        return userRepository.add(user);
    }

    @Override
    public List<Book> booksInBasket(int userId) {
        if(userRepository.get(userId) != null){
            return userRepository.booksInBasket(userId);
        }else return null;
    }

    @Override
    public Integer numberOfBorrowedBooks(int userId) {
        return userRepository.numberOfBorrowedBooks(userId);
    }

    @Override
    public List<Book> borrowedBooks(int user_id) {
        return userRepository.borrowedBooks(user_id);
    }

}
