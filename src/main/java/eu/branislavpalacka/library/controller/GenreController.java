package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Genre;
import eu.branislavpalacka.library.services.api.GenreService;
import eu.branislavpalacka.library.services.api.request.UpdateGenreRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genre")
public class GenreController {
    private GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable int id){
        Genre genre = genreService.get(id);
        if (genre != null){
            return new ResponseEntity<>(genre, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Genre> genres = genreService.getGenres();
        return new ResponseEntity(genres,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Genre genre){
        Integer id = genreService.add(genre);
        if (id != null){
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody UpdateGenreRequest updateGenreRequest){
        if (genreService.get(id) != null){
            genreService.update(id, updateGenreRequest);
            return ResponseEntity
                    .ok()
                    .build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Genre with id: "+id+" NOT FOUND");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete (@PathVariable int id){
        if(genreService.get(id) != null){
            genreService.delete(id);
            return ResponseEntity
                    .ok()
                    .build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Genre with id: "+id+" NOT FOUND");
        }
    }
}
