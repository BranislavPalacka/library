package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.repository.BookRepository;
import eu.branislavpalacka.library.services.api.BookService;
import eu.branislavpalacka.library.services.api.request.UpdateBookRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.getAll();
    }

    @Override
    public Book get(int id) {
        return bookRepository.get(id);
    }

    @Override
    public Integer add(Book book) {
        return bookRepository.add(book);
    }

    @Override
    public void update(int id, UpdateBookRequest updateBookRequest) {
        bookRepository.update(id, updateBookRequest);
    }

    @Override
    public void delete(int id) {
        bookRepository.delete(id);
    }

    @Override
    public void addBookToOrder(int book_id, int order_id) {
        bookRepository.addBookToOrder(book_id, order_id);
    }

    @Override
    public void addBookToBasket(int book_id, int basket_id) {
        bookRepository.addBookToBasket(book_id, basket_id);
    }
}
