package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.repository.BooksAuthorsRepository;
import eu.branislavpalacka.library.services.api.BooksAuthorsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksAuthorsServiceImpl implements BooksAuthorsService {
    private final BooksAuthorsRepository booksAuthorsRepository;

    public BooksAuthorsServiceImpl(BooksAuthorsRepository booksAuthorsRepository) {
        this.booksAuthorsRepository = booksAuthorsRepository;
    }

    @Override
    public List<Book> getBooksFromAuthor(int author_id) {
        return booksAuthorsRepository.getBooksFromAuthor(author_id);
    }

    @Override
    public List<Author> getAuthorsOfBook(int book_id) {
        return booksAuthorsRepository.getAuthorsOfBook(book_id);
    }
}
