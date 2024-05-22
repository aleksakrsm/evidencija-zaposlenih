/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.dto.JwtDto;
//import rs.ac.bg.fon.njt.webapp.dto.RegLinkCheckDto;
import rs.ac.bg.fon.njt.webapp.dto.RegPageLinkDto;
import rs.ac.bg.fon.njt.webapp.security.communication.AuthenticationRequest;
import rs.ac.bg.fon.njt.webapp.security.communication.AuthenticationResponse;
import rs.ac.bg.fon.njt.webapp.security.service.AuthenticationService;
import rs.ac.bg.fon.njt.webapp.security.communication.RegisterRequest;

/**
 * Controller for handling user authentication and registration operations. This
 * controller provides endpoints for user registration, sending registration
 * links, checking registration links, and user authentication. Cross-Origin
 * Resource Sharing (CORS) is enabled for all endpoints.
 *
 * @author aleks
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Endpoint to register a new user.
     *
     * @param request The RegisterRequest object containing user registration
     * data.
     * @return ResponseEntity containing the authentication response and HTTP
     * status OK.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Endpoint to send a registration link to the user's email.
     *
     * @param dto The RegPageLinkDto object containing the email address.
     * @return ResponseEntity containing the JWT token and HTTP status OK.
     */
    @PostMapping("/sendRegistrationLink")
    public ResponseEntity<JwtDto> sendRegistrationLink(@RequestBody RegPageLinkDto dto) {
        return ResponseEntity.ok(new JwtDto(authenticationService.getJwtAndSendEmail(dto)));
    }

    /**
     * Endpoint to check the validity of a registration link.
     *
     * @param token The registration token.
     * @param email The email address associated with the registration link.
     * @return ResponseEntity containing a boolean indicating the validity of
     * the link and HTTP status OK.
     */
    @GetMapping("/checkLink")
    public ResponseEntity<Boolean> checkLink(@RequestParam String token, @RequestParam String email) {
//        System.out.println(token);
//        System.out.println(email);
        return ResponseEntity.ok(authenticationService.checkLink(token, email));
    }

    /**
     * Endpoint to authenticate a user.
     *
     * @param request The AuthenticationRequest object containing user
     * credentials.
     * @return ResponseEntity containing the authentication response and HTTP
     * status OK.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        System.out.println("=============AUTHENTICATE======================" + request.getUsername());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
