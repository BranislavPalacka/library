package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.Genre;
import eu.branislavpalacka.library.services.api.*;
import eu.branislavpalacka.library.services.api.request.UpdateBookRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {
    private final BookService bookService;
    private final BooksAuthorsService booksAuthorsService;
    private final BooksGeneresService booksGeneresService;
    private final OrderService orderService;
    private final BasketService basketService;

    public BookController(BookService bookService, BooksAuthorsService booksAuthorsService, BooksGeneresService booksGeneresService,
                          OrderService orderService, BasketService basketService) {
        this.bookService = bookService;
        this.booksAuthorsService = booksAuthorsService;
        this.booksGeneresService = booksGeneresService;
        this.orderService = orderService;
        this.basketService = basketService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        Book book = bookService.get(id);
        if (book == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(book,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAll(){
        List<Book> books = bookService.getBooks();
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Book book){
        Integer id = bookService.add(book);
        if (id == null){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }else{
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody UpdateBookRequest updateBookRequest){
        if (bookService.get(id) != null){
            bookService.update(id, updateBookRequest);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Book with id: "+id+" NOT FOUND.");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id){
        if (bookService.get(id) != null){
            bookService.delete(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Book with id: "+id+" NOT FOUND.");
        }
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity getAuthorsOfBook(@PathVariable("id") int book_id){
        List<Author> authors = booksAuthorsService.getAuthorsOfBook(book_id);
            return new ResponseEntity<>(authors,HttpStatus.OK);
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity getGenresForBook(@PathVariable("id") int book_id){
        List<Genre> genres = booksGeneresService.getGenresForBook(book_id);
        return new ResponseEntity<>(genres,HttpStatus.OK);
    }

    @PatchMapping("/order/{orderID}O_{bookID}B")
    public ResponseEntity addBookToOrder(@PathVariable ("bookID") int book_id,@PathVariable ("orderID") int order_id){
        if(orderService.get(order_id) == null){
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Order with id: "+order_id+" NOT FOUND");
        }else if(bookService.get(book_id) == null) {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Book with id: "+book_id+" NOT FOUND.");
        }else{
            bookService.addBookToOrder(book_id, order_id);
            return ResponseEntity
                    .ok()
                    .build();
        }
    }

    @PatchMapping("/basket/{basketID}B_{bookID}B")
    public ResponseEntity addBookToBasket(@PathVariable ("bookID") int book_id,@PathVariable ("basketID") int basket_id){
        if(basketService.get(basket_id) == null){
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Basket with id: "+basket_id+" NOT FOUND");
        }else if(bookService.get(book_id) == null) {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Book with id: "+book_id+" NOT FOUND.");
        }else{
            bookService.addBookToBasket(book_id, basket_id);
            return ResponseEntity
                    .ok()
                    .build();
        }
    }


}
