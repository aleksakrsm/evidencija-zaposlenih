/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.security.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;

/**
 *
 * @author aleks
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("AUTHORIZATION HEADER______________________________________");
        System.out.println(authHeader);
        String jwt = authHeader.substring(7);
        System.out.println("JWT______________________________________");
        System.out.println(jwt);
        String username = jwtService.extractUsername(jwt);//izvuci iz tokena, a za to mi treba klasa
        System.out.println("USERNAME______________________________________");
        System.out.println(username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("u filteru u if statementu---------------------");
            //u ovom slucaju treba da izvrsim autentikaciju
            //proveriti korisnika iz baze
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                Object details = new WebAuthenticationDetailsSource().buildDetails(request);
                authToken.setDetails(details);
                SecurityContextHolder.setContext(new SecurityContextImpl(authToken));
            }
        }
        filterChain.doFilter(request, response);
    }

}
