package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Genre;
import eu.branislavpalacka.library.repository.GenreRepository;
import eu.branislavpalacka.library.services.api.GenreService;
import eu.branislavpalacka.library.services.api.request.UpdateGenreRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.getAll();
    }

    @Override
    public Genre get(int id) {
        return genreRepository.get(id);
    }

    @Override
    public Integer add(Genre genre) {
        return genreRepository.add(genre);
    }

    @Override
    public void update(int id, UpdateGenreRequest updateGenreRequest) {
        genreRepository.update(id, updateGenreRequest);
    }

    @Override
    public void delete(int id) {
        genreRepository.delete(id);
    }
}