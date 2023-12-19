package ro.sda.final_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.final_project.entities.User;
import ro.sda.final_project.services.UserService;
import ro.sda.final_project.utils.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/") //aceasta metoda se va apela cand vom avea un request de tipul get
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> userList = userService.findAll();
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User's list")
                .data(userList)
                .build();
        return ResponseEntity.ok(response); //punem "ok" daca totul a decurs bine
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> createUser(@RequestBody User user) { //ceea ce va primi in body il va transforma in ceva de tipul user
        User savedUser = userService.createUser(user); //apelam metoda createUser din service pentru a salva user-ul din baza de date
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User added successfully")
                .data(savedUser)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User updated successfully")
                .data(updatedUser)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable ("id") Integer id) {
        System.out.println("The User with the Id" + id + " will be deleted!!!");
        userService.deleteById(id);
        ApiResponse response = new ApiResponse.Builder()
                .status(200)
                .message("User deleted successfully")
                .data(null)
                .build();
        return ResponseEntity.ok(response);
    }
}
