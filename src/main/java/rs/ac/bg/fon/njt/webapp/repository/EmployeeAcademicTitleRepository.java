/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeAcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.HistoryItemID;

/**
 *
 * Repository interface for accessing and managing EmployeeAcademicTitle
 * entities in the database.
 *
 * @author aleks
 */
@Repository
public interface EmployeeAcademicTitleRepository extends JpaRepository<EmployeeAcademicTitle, HistoryItemID> {

    /**
     * Retrieves a list of EmployeeAcademicTitle entities for a specific
     * employee ID, ordered by begin date in ascending order.
     *
     * @param employeeId The ID of the employee.
     * @return A list of EmployeeAcademicTitle entities associated with the
     * specified employee ID, ordered by begin date in ascending order.
     */
    @Query(value = "SELECT * FROM employeeacademictitle WHERE employee = :employeeId ORDER BY begin_date ASC ", nativeQuery = true)
    List<EmployeeAcademicTitle> findByEmployeeIdOrderByBeginDateAsc(@Param("employeeId") Long employeeId);
}
