package com.example.userms.controller;

import com.example.userms.data.User;
import com.example.userms.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/users")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable int id)
    {
        userService.deleteUserById(id);
    }

    @PutMapping(path="/users")
    public User updateUser(@RequestBody User user)
    {
        return userService.updateUser(user);
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping(path = "/users/login")
    public List<User> userLogIn(@RequestBody Map<String,String> requestBody){
        String role = requestBody.get("role");
        String identifier = requestBody.get("identifier");
        String password = requestBody.get("password");
        boolean authentication= userService.userLogIn(role, identifier, password);
        if(authentication){
            return userService.findUserIdByRoleAndEmailOrPhoneNo(role, identifier);

        }
        else{
            return null;
        }
    }

    @PatchMapping(path = "/users/{id}")
    public User updateEmailAndPhoneNoByUserId(@PathVariable int id,@RequestBody Map<String,String> requestBody){
        String email = requestBody.get("email");
        String phone_no = requestBody.get("phone_no");
        return userService.updateEmailAndPhoneNoByUserId(id, email, phone_no);
    }

    @PatchMapping(path = "/users/{id}/reset_password")
    public User updatePasswordByUserId(@PathVariable int id, @RequestBody Map<String,String> requestBody){
        String password = requestBody.get("password");
        return userService.updatePasswordByUserId(id, password);
    }

}
