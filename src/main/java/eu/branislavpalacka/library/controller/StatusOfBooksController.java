package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.StatusOfBooks;
import eu.branislavpalacka.library.services.api.StatusOfBookService;
import eu.branislavpalacka.library.services.api.request.UpdateStatusOfBooksRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("status_of_books")
public class StatusOfBooksController {
    private final StatusOfBookService statusOfBookService;

    public StatusOfBooksController(StatusOfBookService statusOfBookService) {
        this.statusOfBookService = statusOfBookService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable int id) {
        StatusOfBooks statusOfBooks = statusOfBookService.get(id);
        if (statusOfBooks != null){
            return new ResponseEntity<>(statusOfBooks, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<StatusOfBooks> statusOfBooksList = statusOfBookService.getStatusesOfBook();
        return new ResponseEntity<>(statusOfBooksList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(StatusOfBooks statusOfBooks){
        Integer id = statusOfBookService.add(statusOfBooks);
        if (id != null){
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody UpdateStatusOfBooksRequest updateStatusOfBooksRequest){
        if (statusOfBookService.get(id) != null){
            statusOfBookService.update(id, updateStatusOfBooksRequest);
            return ResponseEntity
                    .ok()
                    .build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Status of Book with id= "+id+" NOT FOUND");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable int id){
        if(statusOfBookService.get(id) != null){
            statusOfBookService.delete(id);
            return ResponseEntity
                    .ok()
                    .build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Status of Book with id= "+id+" NOT FOUND");
        }
    }
}
