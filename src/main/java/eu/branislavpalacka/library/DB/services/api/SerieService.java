package eu.branislavpalacka.library.DB.services.api;

import eu.branislavpalacka.library.domain.Serie;
import eu.branislavpalacka.library.DB.services.api.request.UpdateSerieRequest;
import org.springframework.lang.Nullable;

import java.util.List;

public interface SerieService {
    @Nullable
    Serie get(int id);

    @Nullable
    List<Serie> getSeries();

    Integer add(Serie serie);

    void update(int id, UpdateSerieRequest updateSerieRequest);

    void delete(int id);

}
