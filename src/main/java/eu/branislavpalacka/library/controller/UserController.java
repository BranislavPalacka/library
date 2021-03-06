package eu.branislavpalacka.library.controller;

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
}
