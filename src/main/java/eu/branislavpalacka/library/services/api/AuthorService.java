package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.services.api.request.UpdateAuthorRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface AuthorService {
    List<Author> getAuthors();

    @Nullable
    Author get(int id);

    @Nullable
    Integer add(Author author);

    void delete(int id);

    void update(int id, UpdateAuthorRequest updateAuthorRequest);
}
