package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.repository.AuthorRepository;
import eu.branislavpalacka.library.repository.BookRepository;
import eu.branislavpalacka.library.services.api.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author get(int id) {
        return authorRepository.get(id);
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.getAll();
    }

    @Override
    public Integer add(Author author) {
        return authorRepository.add(author);
    }
}

