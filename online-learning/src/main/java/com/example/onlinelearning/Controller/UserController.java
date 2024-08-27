package com.example.onlinelearning.Controller;

import com.example.onlinelearning.POJO.PojoCourse;
import com.example.onlinelearning.POJO.PojoOrder;
import com.example.onlinelearning.POJO.PojoUser;
import com.example.onlinelearning.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<PojoUser>> getAllUsers() {
        List<PojoUser> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PojoUser> createUser(@RequestBody PojoUser pojoUser) {
        PojoUser createdUser = userService.createUser(pojoUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<PojoUser> updateUser(@PathVariable("userId") long id, @RequestBody PojoUser pojoUser) {
        PojoUser updatedUser = userService.updateUser(id, pojoUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PojoUser>> findUserByUsername(@RequestParam String username) {
        List<PojoUser> users = userService.findUserByUsername(username);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}