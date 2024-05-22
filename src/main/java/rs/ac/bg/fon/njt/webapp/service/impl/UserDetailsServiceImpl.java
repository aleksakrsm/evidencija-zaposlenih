/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.njt.webapp.domain.User;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.UserRepository;

/**
 * Service implementation for loading user details by username. This class
 * implements the UserDetailsService interface provided by Spring Security.
 *
 * @author aleks
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Load user details by username. This method retrieves user details from
     * the database based on the provided username.
     *
     * @param username The username of the user whose details are to be loaded.
     * @return UserDetails object representing the user details.
     * @throws InvalidDataException if the provided username is null, empty, or
     * blank, or if the username does not exist in the database.
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        if (username == null || username.isEmpty() || username.isBlank()) {
            throw new InvalidDataException("invalid username");
        }
        Optional<User> optional = userRepository.findByUsername(username);
        if (optional.isEmpty()) {
            throw new InvalidDataException("username ne postoji");
        }
        return optional.get();
    }

}
