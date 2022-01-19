package com.s1127833.webshop.service;

import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public UserService (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserAccount createUser(UserAccount userDetails){

        userRepository.save(userDetails);
        return userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
