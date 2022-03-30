package com.s1127833.webshop;

import com.s1127833.webshop.enums.Role;
import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.repository.UserRepository;
import com.s1127833.webshop.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class initializer {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    public initializer(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void init() {
        if(!userService.doesUserExist("admin")) {
            UserAccount user = new UserAccount();
            user.setEmail("e-mail");
            user.setPassword(bCryptPasswordEncoder.encode("admin"));
            user.setRole(Role.ROLE_OWNER);
            user.setUsername("admin");
            this.userRepository.save(user);
        }
    }
}
