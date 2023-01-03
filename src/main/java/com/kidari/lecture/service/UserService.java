package com.kidari.lecture.service;

import com.kidari.lecture.model.User;
import com.kidari.lecture.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public List<User> getUserList() { return  userRepository.findAll(); }

    @Transactional
    public User register(User user) {
        return userRepository.save(user);
    }
}
