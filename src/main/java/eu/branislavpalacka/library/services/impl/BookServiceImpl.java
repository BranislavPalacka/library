package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.repository.BookRepository;
import eu.branislavpalacka.library.services.api.BookService;
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
}
