package com.example.userms.controller;

import com.example.userms.data.User;
import com.example.userms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path="/users")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id)
    {
        userService.deleteUser(id);
    }

    @PutMapping(path="/users")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @GetMapping(path = "/users/{id}")
    public User getUserByID(@PathVariable int id){
        return userService.getUserByID(id);
    }

    @GetMapping(path = "/users", params = {"role","email","phone_no"})
    public List<User> getUserIdByRoleAndEmailOrPhoneNo(@RequestParam String role,@RequestParam String email,@RequestParam String phone_no){
        return userService.getUserIdByRoleAndEmailOrPhoneNo(role, email, phone_no);
    }
}
