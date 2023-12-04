package com.duckcoffee.app.dao;

import com.duckcoffee.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(@RequestParam("email") String email);
}
