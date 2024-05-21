/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service.impl;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import rs.ac.bg.fon.njt.webapp.domain.User;
import rs.ac.bg.fon.njt.webapp.domain.enums.Role;
import rs.ac.bg.fon.njt.webapp.exception.InvalidDataException;
import rs.ac.bg.fon.njt.webapp.repository.UserRepository;

/**
 *
 * @author aleks
 */
@ExtendWith(MockitoExtension.class)
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1l, "Aleksa", "Krsmanovic", "krsma", "aleksa.krsma@gmail.com", "Sifra123.", Role.USER);
    }

    @Test
    void loadUserByUsernameTestPass() {
        Mockito.when(userRepository.findByUsername("krsma")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("krsma");

        Assertions.assertTrue(userDetails instanceof User);
        User myUser = (User) userDetails;
        Assertions.assertEquals(myUser, user);
    }

    @Test
    void loadUserByUsernameTestFailEmptyName() {
        Assertions.assertThrows(InvalidDataException.class, () -> userDetailsService.loadUserByUsername(""));
    }
    @Test
    void loadUserByUsernameTestFailNullName() {
        Assertions.assertThrows(InvalidDataException.class, () -> userDetailsService.loadUserByUsername(null));
    }

}
