package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Author;
import eu.branislavpalacka.library.domain.Book;
import eu.branislavpalacka.library.services.api.AuthorService;
import eu.branislavpalacka.library.services.api.BooksAuthorsService;
import eu.branislavpalacka.library.services.api.request.UpdateAuthorRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("author")
public class AuthorController {
    private final AuthorService authorService;
    private final BooksAuthorsService booksAuthorsService;

    public AuthorController(AuthorService authorService, BooksAuthorsService booksAuthorsService) {
        this.authorService = authorService;
        this.booksAuthorsService = booksAuthorsService;
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

    @PatchMapping("{id}")
    public ResponseEntity update(@PathVariable("id") int id, @RequestBody UpdateAuthorRequest updateAuthorRequest){
        if (authorService.get(id) != null ){
            authorService.update(id,updateAuthorRequest);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Author with id: "+id+" NOT FOUND.");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable int id){

        if(authorService.get(id) != null){
            Author author = authorService.get(id);

            if (authorService.isUsed(id) == false) {
                authorService.delete(id);
                return ResponseEntity
                        .ok()
                        .build();
            }else{
                return ResponseEntity
                        .status(HttpStatus.PRECONDITION_FAILED)
                        .body("There are still books with author "+author.getName().toUpperCase()+" "+author.getSurname().toUpperCase()+".");
            }

        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Author with id= "+id+" NOT FOUND");
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity getBooksFromAuthor(@PathVariable ("id") int author_id){
        List<Book> books = booksAuthorsService.getBooksFromAuthor(author_id);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
