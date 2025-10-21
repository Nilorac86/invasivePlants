package com.carolin.invasiveplants.JwtAuth;

import com.carolin.invasiveplants.Entity.Role;
import com.carolin.invasiveplants.Entity.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Fetches secret key from Application.properties
    @Value("${app.jwtSecret:mySecretKey}")
    private String jwtSecret;

    // Valid 1 hour
    @Value("3600000")
    private int jwtExpirationMs;

    //Generate JWT-token
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail()) //  email i subject
                .claim("roles", user.getRole().getRoleName())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    // Unlocks token with secret key
    public String getUsernameFromToken(String token) {

        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

    }

    // Validate token that returns token true, if its validated. Otherwise logging error.
    public boolean validateToken(String token) {

        try{
            Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token);
            return true;
        }
        /*catch(SignatureException e){
            logger.error("Invalid JWT signature: {}",e.getMessage());
        }*/
        catch (MalformedJwtException e){
            logger.error("Invalid JWT token: {}",e.getMessage());
        }
        catch (ExpiredJwtException e){
            logger.error("Expired JWT token: {}",e.getMessage());
        }
        catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}",e.getMessage());
        }

        return false;
    }

}
