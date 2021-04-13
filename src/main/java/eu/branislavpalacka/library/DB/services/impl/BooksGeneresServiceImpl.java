package eu.branislavpalacka.library.DB.services.impl;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Genre;
import eu.branislavpalacka.library.DB.repository.BooksGenresRepository;
import eu.branislavpalacka.library.DB.services.api.BooksGeneresService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksGeneresServiceImpl implements BooksGeneresService {
    private final BooksGenresRepository booksGenresRepository;

    public BooksGeneresServiceImpl(BooksGenresRepository booksGenresRepository) {
        this.booksGenresRepository = booksGenresRepository;
    }

    @Override
    public List<Book> getBooksFromGenre(int genre_id) {
        return booksGenresRepository.getBooksFromGenre(genre_id);
    }

    @Override
    public List<Genre> getGenresForBook(int book_id) {
        return booksGenresRepository.getGenresForBook(book_id);
    }
}
