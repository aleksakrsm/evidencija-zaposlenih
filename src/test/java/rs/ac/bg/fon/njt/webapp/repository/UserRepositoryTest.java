/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.njt.webapp.domain.User;
import rs.ac.bg.fon.njt.webapp.domain.enums.Role;

/**
 *
 * @author aleks
 */
@DataJpaTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Aleksa", "Krsmanovic", "aleksa", "aleksa@gmail.com", "aleksa", Role.USER);
        user = userRepository.save(user);
    }

    @AfterEach
    public void tearDown() {
        userRepository.delete(user);
    }

    @Test
    void givenUser_whenSaved_thenCanBeFoundByUsername() {
        User savedUser = userRepository.findByUsername(user.getUsername()).orElse(null);
        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(user.getEmail(),savedUser.getEmail());
        Assertions.assertEquals(user.getPassword(),savedUser.getPassword());
        Assertions.assertEquals(user.getUsername(),savedUser.getUsername());
    }
}
