package Operation.example.Controller;

import Operation.example.CustomeExeption.AlreadyExistException;
import Operation.example.CustomeExeption.ResourceNotFoundException;
import Operation.example.Entity.User;
import Operation.example.ServiceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserCController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody User user) {
        try {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } catch (AlreadyExistException ex) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") Long id) {
        try{
        User user = userService.getUser(id);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException ex ){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllUser() {
        try {
            List<User> allUser = userService.findAll();
            return ResponseEntity.ok(allUser);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete (@PathVariable ("id") Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("user delete successfully "+ id);
        } catch (ResourceNotFoundException ex ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser (@PathVariable ("id") Long id, @RequestBody User user) {
        try {
            userService.update(id, user);
           return ResponseEntity.ok("user update successfully");
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
