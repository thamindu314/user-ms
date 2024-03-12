package com.example.userms.service;

import com.example.userms.data.User;
import com.example.userms.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByID(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public List<User> getUserIdByRoleAndEmailOrPhoneNo(String role, String email, String phone_no){
        return userRepository.findUserIdByRoleAndEmailOrPhoneNo(role, email, phone_no);
    }
}
