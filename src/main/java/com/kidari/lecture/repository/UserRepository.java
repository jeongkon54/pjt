package com.kidari.lecture.repository;

import com.kidari.lecture.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
