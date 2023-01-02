package com.kidari.lecture.service;

import com.kidari.lecture.model.User;
import com.kidari.lecture.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getUserList() {
        return  userRepository.findAll();
    }
}
