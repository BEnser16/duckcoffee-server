package com.duckcoffee.app.service;


import com.duckcoffee.app.dao.UserRepository;
import com.duckcoffee.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserRepository userRepository;

    public void createUser(User user) throws Exception {

        User dbUser = userRepository.findByEmail(user.getEmail());

        if(dbUser != null) {
            throw new Exception("此帳號已經存在.");
        }

        // 用 bcrypt encode user password 之後將 new user 存入資料庫
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }



}
