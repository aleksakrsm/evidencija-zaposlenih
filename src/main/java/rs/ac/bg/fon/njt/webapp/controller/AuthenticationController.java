/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.bg.fon.njt.webapp.security.auth.AuthenticationRequest;
import rs.ac.bg.fon.njt.webapp.security.auth.AuthenticationResponse;
import rs.ac.bg.fon.njt.webapp.security.service.AuthenticationService;
import rs.ac.bg.fon.njt.webapp.security.auth.RegisterRequest;

/**
 *
 * @author aleks
 */
@RestController
@RequestMapping("/user")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;
    
    @PostMapping("/register")// samo admin moze da registruje novog korinika
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    
    @PostMapping("/authenticate")//svako ko hoce da ima nalog za aplikaciju u zaposlene i katedre i sl???
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    
}
