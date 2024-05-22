/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubject;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubjectID;

/**
 * Repository interface for accessing and managing EmployeeSubject entities in
 * the database.
 *
 * @author aleks
 */
@Repository
public interface EmployeeSubjectRepository extends JpaRepository<EmployeeSubject, EmployeeSubjectID> {

    /**
     * Retrieves a list of EmployeeSubject entities by the employee ID.
     *
     * @param id The ID of the employee.
     * @return A list of EmployeeSubject entities associated with the specified
     * employee ID.
     */
    List<EmployeeSubject> findByIdEmployeeId(Long id);

    /**
     * Retrieves a list of EmployeeSubject entities by the subject ID.
     *
     * @param id The ID of the subject.
     * @return A list of EmployeeSubject entities associated with the specified
     * subject ID.
     */
    List<EmployeeSubject> findByIdSubjectId(Long id);
}
