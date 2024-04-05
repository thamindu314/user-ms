package com.example.userms.service;

import com.example.userms.data.User;
import com.example.userms.data.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public List<User> findUserIdByRoleAndEmailOrPhoneNo(String role, String identifier){
        return userRepository.findUserIdByRoleAndEmailOrPhoneNo(role, identifier);
    }

    public User updateEmailAndPhoneNoByUserId(int id,String email,String phone_no){
        User user= userRepository.findById(id).orElse(null);
        if(user==null){
            return null;
        }
        user.setEmail(email);
        user.setPhone_no(phone_no);
        return userRepository.save(user);
    }

    public User updatePasswordByUserId(int id, String password){
        User user= userRepository.findById(id).orElse(null);
        if(user==null){
            return null;
        }
        user.setPassword(password);
        return userRepository.save(user);
    }

    public boolean userLogIn(String role, String identifier, String password) {
        // Check if the identifier is a valid email or phone number
        User user = userRepository.findUserIdByRoleAndEmailOrPhoneNo(role, identifier).stream().findFirst().orElse(null);

        // Check if the user exists and the password matches
        return user != null && user.getPassword().equals(password); // Authentication successful
    }

}
