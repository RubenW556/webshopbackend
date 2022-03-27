package com.s1127833.webshop;

import com.s1127833.webshop.enums.Role;
import com.s1127833.webshop.model.UserAccount;
import com.s1127833.webshop.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class initializer {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    public initializer(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    private void init() {
        if(userService.loadUserByUsername("admin")== null) {
            UserAccount user = new UserAccount();
            user.setEmail("e-mail");
            user.setPassword(bCryptPasswordEncoder.encode("admin"));
            user.setRole(Role.ROLE_OWNER);
            user.setUsername("admin");
            this.userService.createUser(user);
        }
    }
}
