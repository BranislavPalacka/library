package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Basket;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.services.api.BasketService;
import eu.branislavpalacka.library.services.api.request.UpdateBasketRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("basket")
public class BasketController {
    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable int id){
        Basket basket = basketService.get(id);
        if(basket == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(basket,HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Basket> basketList = basketService.getAll();
        return new ResponseEntity<>(basketList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Basket basket){
        Integer id = basketService.add(basket);
        if (id == null){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody UpdateBasketRequest updateBasketRequest){
        if(basketService.get(id) != null){
            basketService.update(id, updateBasketRequest);
            return ResponseEntity
                    .ok()
                    .build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Basket with id: "+id+" NOT FOUND.");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id){
        String result = basketService.delete(id);
        if(result.equals("deleted")){
            return ResponseEntity
                    .ok()
                    .build();
        }else if (result.equals("isUsed")){
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Basket with id: "+id+" is not empty.");
        }else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Basket with id: "+id+" NOT FOUND.");
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity getAllBooksInBasket(@PathVariable int id){
        Basket basket = basketService.get(id);
        List<Book> bookList = basketService.getAllBooksInBasket(id);
        if(basket == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(bookList,HttpStatus.OK);
        }
    }
}
