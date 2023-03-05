package com.example.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    public String generateToken(Claims claims) {
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + 10000000000L;
        Date exp = new Date(expMillis);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, "secret").compact();
    }

    public boolean validateToken(final String token) {
        boolean isTokenValid = false;
        try {
            Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
            isTokenValid = true;
        } catch (RuntimeException e) {
            log.error(e.getMessage());
        }
        return isTokenValid;
    }

    public Claims getClaims(String token){
        try{
            return Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            System.out.println(e.getMessage() + "=>" + e);
        }
        return null;
    }
}
