package com.duckcoffee.app;

import com.duckcoffee.app.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 測試 初始化 JWT Token
     */
    @Test
    void generateJwtTest() {
        Map<String, String> claims = new HashMap<>();
        String subject = "IdenttiyId";
        String jti = "";
        String jwtToken = jwtUtil.generateToken(claims, subject , jti);
        assertTrue(jwtToken.length() > 0);
    }

    /**
     * 測試 取得單一屬性
     */
    @Test
    void getClaimTest() {
        // 推入假屬性 nmae
        Map<String, String > claims = new HashMap<>();
        claims.put("name", "Bob");
        String jti = "";
        String subject = "10083";
        String jwtToken = jwtUtil.generateToken(claims, subject , jti);

    }


    /**
     * 測試 Token到期是否正常
     */
    @Test
    void validateTokenTest() throws InterruptedException {

        String subject = "10083";
        String jwtToken = jwtUtil.generateToken(new HashMap<>(), subject , "");

        // 測試未過期情況
        assertTrue(jwtUtil.validateToken(jwtToken));

        // 測試到期情況
        assertThrows(ExpiredJwtException.class, () -> {
            Thread.sleep(5000);
            jwtUtil.validateToken(jwtToken);
        });

    }


}
