package com.example.CompleteSpringDemo.controller;

import com.example.CompleteSpringDemo.model.User;
import com.example.CompleteSpringDemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all-active")
    public List<User> getAllActiveUsers() {
        return userService.getAllActiveUsers();
    }

    @GetMapping("/all")
    public List<User> getAllUsersIncludingSoftDeleted() {
        return userService.getAllUsersIncludingSoftDeleted();
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/sorted")
    public List<User> getSortedUsers() {
        return userService.getUsersSortedByAge();
    }
    @PutMapping("/update/{id}")
    public User updateUserById(@PathVariable int id, @RequestBody User updatedUser) {
        return userService.updateUserById(id, updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public void softDeleteUser(@PathVariable int id) {
        userService.softDeleteUser(id);
    }
}
