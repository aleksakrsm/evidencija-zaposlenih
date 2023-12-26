/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.security.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.domain.User;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.UserRepository;
import rs.ac.bg.fon.njt.webapp.security.Role;
import rs.ac.bg.fon.njt.webapp.security.auth.AuthenticationRequest;
import rs.ac.bg.fon.njt.webapp.security.auth.AuthenticationResponse;
import rs.ac.bg.fon.njt.webapp.security.auth.RegisterRequest;
import rs.ac.bg.fon.njt.webapp.security.service.JwtService;

/**
 *
 * @author aleks
 */
@Service
public class AuthenticationService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired 
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    
    
    public AuthenticationResponse register(RegisterRequest request){
        var user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
               
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(), request.getPassword()
                        )
                );
        
        if(request.getUsername() == null || request.getUsername().isEmpty() || request.getUsername().isBlank())
            throw new InvalidDataException("invalid username");
        Optional<User> optional = userRepository.findByUsername(request.getUsername());
        if(optional.isEmpty())
            throw new InvalidDataException("username ne postoji");
        User user =  optional.get();
        var jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }
    
}
