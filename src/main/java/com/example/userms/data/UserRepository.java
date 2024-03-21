package com.example.userms.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.role=?1 AND u.email=?2 OR u.phone_no=?2")
    List<User> findUserIdByRoleAndEmailOrPhoneNo(String role, String identifier);

    @Query("UPDATE User u SET u.email=?2, u.phone_no=?3 WHERE u.user_id=?1")
    void updateEmailAndPhoneNoByUserID(int id,String email,String phone_no);

}
