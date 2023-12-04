package com.duckcoffee.app.controller;


import com.duckcoffee.app.entity.User;
import com.duckcoffee.app.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/auth/member")
public class MemberController {


    public MemberService memberService;


    public AuthenticationManager authenticationManager;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        try {

            memberService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status" , true , "message" , "註冊成功"
            ));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "status" , false , "message" , "註冊失敗"
                    )
            );

        }

    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                    user.getEmail() , user.getPassword()
            );

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status" , true , "message" , "登入成功"
            ));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status" , false , "message" , "登入失敗"
            ));
        }
    }


}
