package com.duckcoffee.app.controller;


import com.duckcoffee.app.entity.User;
import com.duckcoffee.app.service.MemberService;
import com.duckcoffee.app.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;


@RestController
@RequestMapping(path = "/api/auth/member")
@CrossOrigin("http://localhost:3000")
public class MemberController {

    @Autowired
    public MemberService memberService;

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    public JwtUtil jwtUtil;

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody User user) {
        try {
            System.out.println("touch register api!!!");
            memberService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status" , true , "message" , "註冊成功"
            ));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "status" , false , "message" , "註冊失敗"
                    )
            );

        }

    }

    @GetMapping (path = "/getuser")
    public ResponseEntity<Object> touch() {
        try {
            System.out.println("touch get test api!!!");

            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status" , true , "message" , "touch成功"
            ));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Map.of(
                            "status" , false , "message" , "touch失敗"
                    )
            );

        }

    }


    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody Map<String , String> loginRequest) {
        try {
            System.out.println("using login api!!!");
            String email = loginRequest.get("email");
            String password = loginRequest.get("password");
            System.out.println("this is login email and password:" + email + "p:" + password);

            // 依照身份創建 驗證用 token
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(email, password);

            // 依照 token 進行身份驗證
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            // 將驗證成功的身份信息放入上下文
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 生成令牌
            String loginToken = jwtUtil.generateToken(Collections.emptyMap(), email, null);

            // 身份验证成功后的处理
            return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                    "status", true, "token", loginToken, "message", "登入成功" , "email" , email
            ));
        } catch (AuthenticationException ex) {
            // 处理身份验证失败
            System.out.println(ex.getMessage());
            ex.printStackTrace();

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                    "status", false, "message", "登入失敗"
            ));
        }
    }



}
