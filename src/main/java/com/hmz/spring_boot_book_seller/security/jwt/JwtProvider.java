package com.hmz.spring_boot_book_seller.security.jwt;


import com.hmz.spring_boot_book_seller.security.UserPrincipal;
import com.hmz.spring_boot_book_seller.util.SecurityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;



@Component
public class JwtProvider implements IJwtProvider {
    @Value("${app.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_TIME_IN_MS;

    @Override
    public String generateToken(UserPrincipal auth) {
        String authorities = auth.getAuthorities().stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(auth.getUsername())
                .claim("roles", authorities)
                .claim("userId", auth.getId())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_TIME_IN_MS))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }


    @Override
    public Authentication getAuthentication(HttpServletRequest request) {

        Claims claims = extractAllClaims(request);
        if (claims == null) {
            return null;
        }

        String username = claims.getSubject();
        Long userId = claims.get("userId", Long.class);

        Set<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SecurityUtils::convertToAuthority)
                .collect(Collectors.toSet());

        UserDetails userDetails = UserPrincipal.builder()
                .username(username)
                .id(userId)
                .authorities(authorities)
                .build();

        if (username == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

    }

    @Override
    public boolean validateToken(HttpServletRequest request) {
        Claims claims = extractAllClaims(request);
        if (claims == null) {
            return false;
        }

        if (claims.getExpiration().before(new Date())) {
            return false;
        }

        return true;

    }

    private Claims extractAllClaims(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null) {
            return null;
        }

        return Jwts.parser().
                setSigningKey(JWT_SECRET).
                parseClaimsJws(token).
                getBody();

    }


}
