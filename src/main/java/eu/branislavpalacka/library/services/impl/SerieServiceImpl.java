package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Serie;
import eu.branislavpalacka.library.repository.SerieRepository;
import eu.branislavpalacka.library.services.api.SerieService;
import eu.branislavpalacka.library.services.api.request.UpdateSerieRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImpl implements SerieService {
    private final SerieRepository serieRepository;

    public SerieServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public Serie get(int id) {
        return serieRepository.get(id);
    }

    @Override
    public List<Serie> getSeries() {
        return serieRepository.getAll();
    }

    @Override
    public Integer add(Serie serie) {
        return serieRepository.add(serie);
    }

    @Override
    public void update(int id, UpdateSerieRequest updateSerieRequest) {
        serieRepository.update(id,updateSerieRequest);
    }

    @Override
    public void delete(int id) {
        serieRepository.delete(id);
    }
}
