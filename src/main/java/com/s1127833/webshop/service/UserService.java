package com.s1127833.webshop.service;

import com.s1127833.webshop.enums.Role;
import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SanitizationService sanitizationService;


    public UserService (UserRepository userRepository, BCryptPasswordEncoder encoder, SanitizationService sanitizationService){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = encoder;
        this.sanitizationService = sanitizationService;
    }

    public void createUser(UserAccount userDetails){
        userDetails.setPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));

        if(doesUserExist(userDetails.getUsername())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "account already exists");
        }

        if(sanitizationService.CheckSanitize(userDetails.getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "only use valid characters");
        }
        if(sanitizationService.CheckSanitize(userDetails.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "only use valid characters");
        }

        if(userDetails.hasRole(Role.ROLE_OWNER)){
            if (!((UserAccount) SecurityContextHolder.getContext().getAuthentication()).hasRole(Role.ROLE_OWNER)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,"can't create owner without being an owner");
            }
        }
        userRepository.save(userDetails);
    }

    public boolean doesUserExist(String userName){
        return (null != userRepository.findByUsername(userName));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
