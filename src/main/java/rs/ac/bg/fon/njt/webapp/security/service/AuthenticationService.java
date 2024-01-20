/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.security.service;

import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.domain.User;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.UserRepository;
import rs.ac.bg.fon.njt.webapp.domain.Role;
import rs.ac.bg.fon.njt.webapp.security.communication.AuthenticationRequest;
import rs.ac.bg.fon.njt.webapp.security.communication.AuthenticationResponse;
import rs.ac.bg.fon.njt.webapp.security.communication.RegisterRequest;
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

    public AuthenticationResponse register(RegisterRequest request) {
        @Valid
        User user = new User(request.getFirstname(),
                request.getLastname(),
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                Role.USER);

        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new InvalidDataException("username is taken");
        }

        user = userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (request.getUsername() == null || request.getUsername().isEmpty() || request.getUsername().isBlank()) {
            throw new InvalidDataException("invalid username");
        }
        Optional<User> optional = userRepository.findByUsername(request.getUsername());
        if (optional.isEmpty()) {
            throw new InvalidDataException("username ne postoji");
        }
        authenticationManager.
                authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(), request.getPassword()
                        )
                );
        User user = optional.get();
        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt);
    }

}
