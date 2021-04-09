package eu.branislavpalacka.library.services.impl;

import eu.branislavpalacka.library.domain.Basket;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.repository.BasketRepository;
import eu.branislavpalacka.library.services.api.BasketService;
import eu.branislavpalacka.library.services.api.request.UpdateBasketRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @Override
    public Basket get(int id) {
        return basketRepository.get(id);
    }

    @Override
    public Integer add(Basket basket) {
        return basketRepository.add(basket);
    }

    @Override
    public List<Basket> getAll() {
        return basketRepository.getAll();
    }

    @Override
    public void update(int id,UpdateBasketRequest updateBasketRequest) {
        basketRepository.update(id, updateBasketRequest);
    }


    @Override
    public String delete(int id) {
        if(basketRepository.get(id) != null){
           if(basketRepository.getAllBooksInBasket(id).size() == 0){
               basketRepository.delete(id);
               return "deleted";
           }else return "isUsed";
        }else return ("notFound");
    }

    @Override
    public List<Book> getAllBooksInBasket(int id) {
        return basketRepository.getAllBooksInBasket(id);
    }
}
