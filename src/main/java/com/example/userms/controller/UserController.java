package com.example.userms.controller;

import com.example.userms.data.User;
import com.example.userms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
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

    @PostMapping(path = "/users/login")
    public List<User> userLogIn( @RequestBody Map<String,String> requestBody){
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

    @PutMapping(path = "/users/{id}")
    public void updateEmailAndPhoneNoByUserID(@PathVariable int id,@RequestBody String email,String phone_no){
        userService.updateEmailAndPhoneNoByUserID(id, email, phone_no);
    }

    @PatchMapping(path = "/users/{id}")
    public User updatePasswordByUserID(@PathVariable int id, @RequestBody Map<String,String> requestBody){
        String password = requestBody.get("password");
        return userService.updatePasswordByUserID(id, password);
    }
}
