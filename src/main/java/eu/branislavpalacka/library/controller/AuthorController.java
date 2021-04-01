package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.services.api.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable int id){
        Author author = authorService.get(id);

        if (author != null){
            return new ResponseEntity<>(author, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping()
    public ResponseEntity getAll(){
        List<Author> authors = authorService.getAuthors();
        return new ResponseEntity<>(authors,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Author author){
        Integer id = authorService.add(author);
        if (id != null){
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
