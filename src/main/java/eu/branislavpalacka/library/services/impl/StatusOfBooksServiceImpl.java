package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.StatusOfBooks;
import eu.branislavpalacka.library.repository.StatusOfBooksRepository;
import eu.branislavpalacka.library.services.api.StatusOfBookService;
import eu.branislavpalacka.library.services.api.request.UpdateStatusOfBooksRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusOfBooksServiceImpl implements StatusOfBookService {
    private final StatusOfBooksRepository statusOfBooksRepository;

    public StatusOfBooksServiceImpl(StatusOfBooksRepository statusOfBooksRepository) {
        this.statusOfBooksRepository = statusOfBooksRepository;
    }

    @Override
    public List<StatusOfBooks> getStatusesOfBook() {
        return statusOfBooksRepository.getAll();
    }

    @Override
    public StatusOfBooks get(int id) {
        return statusOfBooksRepository.get(id);
    }

    @Override
    public Integer add(StatusOfBooks statusOfBooks) {
        return statusOfBooksRepository.add(statusOfBooks);
    }

    @Override
    public void update(int id, UpdateStatusOfBooksRequest updateStatusOfBooksRequest) {
        statusOfBooksRepository.update(id, updateStatusOfBooksRequest);
    }

    @Override
    public void delete(int id) {
        statusOfBooksRepository.delete(id);
    }
}
