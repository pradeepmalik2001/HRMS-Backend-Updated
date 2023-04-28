package com.ahom.hrms.util;

import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JWTUtils {

    private String signature;
    private static final long serialVersionUID = 234234523523L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private String secret = "savit";

        public void extractUsername(String token) {
            extractClaim(token, Claims::getSubject);
        }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        final String auth =userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        Map<String, Object> claims = new HashMap<>();
        claims.put("Roles",auth);
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new
                Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).signWith(SignatureAlgorithm.HS256, secret).compact();
    }
    private String Signature(String token) throws IllegalAccessException {
            String[] parts=token.split("\\.");
            if(parts.length!=3){
                throw new IllegalAccessException("Invalid");
            }
            signature=parts[2];
            return signature;
    }

//    private String extractSignature (String token){
//            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getSignature();
//    }
    public Boolean validateToken(String token, UserDetails userDetails) {
//        final String username = extractUsername(token);
        try {
            Jws<Claims> claimsJws=Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if (claimsJws.getBody().getExpiration().before(new Date()))
            {
                return false;
        }
            return true;

        }catch(JwtException | IllegalArgumentException e){
            throw new RuntimeException("saffdas");
        }
    }



}
