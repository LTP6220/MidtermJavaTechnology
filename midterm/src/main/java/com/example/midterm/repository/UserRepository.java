package com.example.midterm.repository;

import com.example.midterm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean findByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
