/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.webapp.domain.Department;

/**
 * Repository interface for accessing and managing Department entities in the
 * database.
 *
 * @author aleks
 */
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    /**
     * Finds a Department by its name.
     *
     * @param name The name of the Department.
     * @return An Optional containing the found Department, or empty if not
     * found.
     */
    Optional<Department> findByName(String name);

    /**
     * Finds a Department by its short name.
     *
     * @param shortName The short name of the Department.
     * @return An Optional containing the found Department, or empty if not
     * found.
     */
    Optional<Department> findByShortName(String shortName);
}
