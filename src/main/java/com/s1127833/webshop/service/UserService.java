package com.s1127833.webshop.service;

import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

 public UserAccount createUser(UserAccount userAccount){

     userRepository.save(userAccount);
     return userAccount;
 }
}
