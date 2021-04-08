package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.StatusOfBooks;
import eu.branislavpalacka.library.services.api.request.UpdateStatusOfBooksRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface StatusOfBookService {
    List<StatusOfBooks> getStatusesOfBook();

    @Nullable
    StatusOfBooks get(int id);

    @Nullable
    Integer add(StatusOfBooks statusOfBooks);

    void update(int id, UpdateStatusOfBooksRequest updateStatusOfBooksRequest);

    void delete(int id);

    boolean isUsed(int id);
}
