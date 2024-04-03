package com.example.userms.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.role=?1 AND u.email=?2 OR u.phone_no=?2")
    List<User> findUserIdByRoleAndEmailOrPhoneNo(String role, String identifier);

}
