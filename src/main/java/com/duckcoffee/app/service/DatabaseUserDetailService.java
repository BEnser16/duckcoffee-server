package com.duckcoffee.app.service;

import com.duckcoffee.app.dao.UserRepository;
import com.duckcoffee.app.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Component
public class DatabaseUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User dbUser = userRepository.findByEmail(email);

        if(dbUser == null) {
            throw new UsernameNotFoundException("User email not found in db.");
        }

        return new org.springframework.security.core.userdetails.User(
                dbUser.getEmail(),
                dbUser.getPassword(),
                Collections.emptyList()
        );
    }

}
