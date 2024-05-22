/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.webapp.domain.User;

/**
 * Repository interface for accessing and managing User entities in the
 * database.
 *
 * @author aleks
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a User by its username.
     *
     * @param username  The username of the User.
     * @return An Optional containing the found User, or empty if not found.
     */
    Optional<User> findByUsername(String username);
}
