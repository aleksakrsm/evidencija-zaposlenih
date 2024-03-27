/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.security.communication.AuthenticationRequest;
import rs.ac.bg.fon.njt.webapp.security.communication.AuthenticationResponse;
import rs.ac.bg.fon.njt.webapp.security.service.AuthenticationService;
import rs.ac.bg.fon.njt.webapp.security.communication.RegisterRequest;

/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;
     
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request){
        System.out.println("=============AUTHENTICATE======================"+request.getUsername());
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    
}
