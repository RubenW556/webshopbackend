package com.s1127833.webshop.repository;

import com.s1127833.webshop.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, String> {
    UserAccount findByUsername(String username);
}
