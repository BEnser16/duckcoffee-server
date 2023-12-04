package com.duckcoffee.app.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

@Component
public class JwtUtil {

    // create secretKey using HS512
    SecretKey secretKey = Jwts.SIG.HS512.key().build();

    public String generateToken(
            Map<String , String> claims ,
            String subject , String jti
    ) {
        // 設定 token expire time 單位分鐘
        int expireSecond = 1800 * 1000;
        Date expireDate = new Date(System.currentTimeMillis() + expireSecond);

        // 使用 uuid 產生 random id for jwt id
        if(jti == null) {
            UUID uuid = UUID.randomUUID();
            jti = uuid.toString();
        }

        return Jwts.builder().claims(claims).subject(subject).id(jti).issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expireDate).signWith(secretKey).compact();

    }

    // get token 定義資料
    public <T> T getClaimFromToken(String token , Function<Claims , T> ClaimsResolver) {
        final Claims claims = parseToken(token);

        return ClaimsResolver.apply(claims);
    }

    // 解析 token 資料
    private Claims parseToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }


    // 驗證 token 逾時
    public Boolean validateToken(String token) {
        // 這裡用 Claims內建的 getExpiration 取得到期時間
        final Date expDate= getClaimFromToken(token, Claims::getExpiration);
        return !expDate.before(new Date());
    }

}
