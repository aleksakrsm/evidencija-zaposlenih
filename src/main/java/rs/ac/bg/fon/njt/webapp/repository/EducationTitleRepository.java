/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;

/**
 * Repository interface for accessing and managing EducationTitle entities in
 * the database.
 *
 * @author aleks
 */
public interface EducationTitleRepository extends JpaRepository<EducationTitle, Long> {

    /**
     * Finds a EducationTitle by its name.
     *
     * @param name The name of the EducationTitle.
     * @return An Optional containing the found EducationTitle, or empty if not
     * found.
     */
    Optional<EducationTitle> findByName(String name);
}
