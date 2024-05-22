/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.webapp.domain.Subject;

/**
 * Repository interface for accessing and managing Subject entities in the
 * database.
 *
 * @author aleks
 */
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>, JpaSpecificationExecutor<Subject> {

    /**
     * Finds a Subject by its name.
     *
     * @param name The name of the Subject.
     * @return An Optional containing the found Subject, or empty if not found.
     */
    Optional<Subject> findByName(String name);

    /**
     * Finds all Subjects based on the given Specification.
     *
     * @param specification The Specification to filter Subjects.
     * @return A list of Subjects matching the Specification.
     */
    List<Subject> findAll(Specification<Subject> specification);

    /**
     * Finds Subjects by their studies type, paginated.
     *
     * @param studiesType The studies type of the Subjects to find.
     * @param pageable The pagination information.
     * @return A Page containing the Subjects with the specified studies type.
     */
    Page<Subject> findByStudiestype(String studiesType, Pageable pageable);
}
