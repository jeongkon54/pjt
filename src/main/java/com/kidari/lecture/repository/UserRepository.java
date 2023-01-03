package com.kidari.lecture.repository;

import com.kidari.lecture.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "select * from user where user_number = ?1 ", nativeQuery = true)
    User findByUserNumber(String userNumber);
}
