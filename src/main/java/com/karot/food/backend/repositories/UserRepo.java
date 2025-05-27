package com.karot.food.backend.repositories;

import com.karot.food.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
//    User findByEmailToString(String email);
    @Query("select u.email from User u where u.role = 0")
    List<String> findAllAdmin();
}
