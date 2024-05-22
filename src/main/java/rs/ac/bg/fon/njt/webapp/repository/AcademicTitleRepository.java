/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;

/**
 * Repository interface for accessing and managing AcademicTitle entities in the
 * database.
 *
 * @author aleks
 */
public interface AcademicTitleRepository extends JpaRepository<AcademicTitle, Long> {

    /**
     * Finds a AcademicTitle by its name.
     *
     * @param name The name of the AcademicTitle.
     * @return An Optional containing the found AcademicTitle, or empty if not
     * found.
     */
    Optional<AcademicTitle> findByName(String name);
}
