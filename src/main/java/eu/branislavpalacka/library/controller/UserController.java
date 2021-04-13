package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.domain.User;
import eu.branislavpalacka.library.services.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
        User user = userService.get(id);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return  new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getAll(){
        List<User> userList = userService.getUsers();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody User user){
        Integer id = userService.add(user);
        if (id != null){
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/basket/{id}")
    public ResponseEntity booksInBasket(@PathVariable ("id") int id){
        if(userService.get(id) != null) {
            List<Book> bookList = userService.booksInBasket(id);
            if (bookList.get(0) != null) {
                return new ResponseEntity<>(bookList, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
            }
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("User with id: "+id+" NOT FOUND");
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity borrowedBooks(@PathVariable ("id") int id){
        if(userService.get(id) != null) {
            List<Book> bookList = userService.borrowedBooks(id);
            if (bookList.get(0) != null) {
                return new ResponseEntity<>(bookList, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
            }
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("User with id: "+id+" NOT FOUND");
        }
    }
}
