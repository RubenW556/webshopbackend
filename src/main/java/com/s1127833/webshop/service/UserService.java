package com.s1127833.webshop.service;

import com.s1127833.webshop.model.User;
import com.s1127833.webshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

 public User createUser(User user){

     userRepository.save(user);
     return user;
 }
}
