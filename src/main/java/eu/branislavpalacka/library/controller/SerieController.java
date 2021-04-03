package eu.branislavpalacka.library.controller;

import eu.branislavpalacka.library.domain.Serie;
import eu.branislavpalacka.library.services.api.SerieService;
import eu.branislavpalacka.library.services.api.request.UpdateSerieRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("serie")
public class SerieController {
    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable int id){
        Serie serie = serieService.get(id);
        if(serie != null){
            return new ResponseEntity<>(serie, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Serie> serieList = serieService.getSeries();
        return new ResponseEntity<>(serieList,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Serie serie){
        Integer id = serieService.add(serie);
        if (id != null){
            return new ResponseEntity<>(id,HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity updateSerie(@PathVariable ("id") int id, @RequestBody UpdateSerieRequest updateSerieRequest){
        if(serieService.get(id) != null){
            serieService.update(id, updateSerieRequest);
            return ResponseEntity
                    .ok()
                    .build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Serie with id= "+id+" NOT FOUND");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id){
        if (serieService.get(id) != null){
            serieService.delete(id);
            return ResponseEntity
                    .ok()
                    .build();
        }else{
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Serie with id= "+id+" NOT FOUND");
        }
    }


}
