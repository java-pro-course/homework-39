package codemika.com.jwtauth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
@Slf4j
@Component
public class JWTUtil {
    public String generateToken(Claims claims){
        long nowMill = System.currentTimeMillis();
        long expMill = nowMill + 1_000_000_000_000_000L;
        Date exp = new Date(expMill);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(nowMill))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, "hello")
                .compact();
    }
    public boolean validateToken(String token){
        token = token.replace("\"","");
        token = token.trim();
        boolean isValid = false;
        try{
            Jwts.parser().setSigningKey("hello").parseClaimsJws(token);
            isValid = true;
        } catch (RuntimeException e){
            log.error(e.getMessage());
        }
        return isValid;
    }
    public Claims getClaims (String token){
        try{
            return Jwts.parser().setSigningKey("hello")
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
}
