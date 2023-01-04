package com.ahom.hrms.filter;

import com.ahom.hrms.config.CustomUserService;
import com.ahom.hrms.util.JWTUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JWTUtils jwtUtils;
    @Autowired
    CustomUserService customUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {

//        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (StringUtils.hasText(header) && StringUtils.hasText(header) && !header.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        final String token = header.split(" ")[1].trim();
//        UserDetails userDetails = customUserService.loadUserByUsername(jwtUtils.getUsernameFromToken(token));
//
//        if (!jwtUtils.validateToken(token, userDetails)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
//                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails == null ? List.of() : userDetails
//                .getAuthorities());
//        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//        filterChain.doFilter(request, response);
//
//
//    }
//}


        final String requesttoken = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (requesttoken != null && requesttoken.startsWith("Bearer ")) {

            token = requesttoken.substring(7);

            try {
                jwtUtils.extractUsername(token);
            } catch (IllegalArgumentException e) {
                System.out.println("unable to get jwt token");
            } catch (ExpiredJwtException e) {
                System.out.println("jwt token has expired");
            } catch (MalformedJwtException e) {
                System.out.println("invalid jwt ");
            }

        } else {
            System.out.println("jwt does not begin with Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.customUserService.loadUserByUsername(username);
            if (this.jwtUtils.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Invalid jwt token");
            }

        } else {
            System.out.println("username is null or context is not null");
        }


        filterChain.doFilter(request, response);

    }
}

