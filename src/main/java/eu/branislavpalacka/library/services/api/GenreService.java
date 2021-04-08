package eu.branislavpalacka.library.services.api;

import eu.branislavpalacka.library.domain.Genre;
import eu.branislavpalacka.library.services.api.request.UpdateGenreRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface GenreService {
    List<Genre> getGenres ();

    @Nullable
    Genre get(int id);

    @Nullable
    Integer add(Genre genre);

    void update(int id, UpdateGenreRequest updateGenreRequest);

    void delete(int id);

    boolean isUsed(int id);


}
