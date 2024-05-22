/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectDto;

/**
 * Service interface for managing employee-subject relationships. Provides
 * methods for saving, editing, finding, and deleting employee-subject
 * relationships.
 *
 * @see EmployeeSubjectDto
 *
 * This interface defines the contract for employee-subject-related operations.
 * Implementations are responsible for the actual logic of these operations. The
 * methods allow for both single and batch operations, as well as specialized
 * operations like saving changes and finding by employee or subject.
 *
 * Note: This interface is typically implemented by a service class that
 * interacts with the data layer.
 *
 * @author aleks
 */
public interface EmployeeSubjectService {

    /**
     * Saves a new employee-subject relationship.
     *
     * @param dto The DTO of the employee-subject relationship to save.
     * @return The saved EmployeeSubjectDto.
     */
    EmployeeSubjectDto save(EmployeeSubjectDto dto);

    /**
     * Saves changes to multiple employee-subject relationships, including
     * deleting specified items.
     *
     * @param toSave The list of DTOs of the employee-subject relationships to
     * save.
     * @param toDelete The list of DTOs of the employee-subject relationships to
     * delete.
     * @return The list of saved EmployeeSubjectDto.
     */
    List<EmployeeSubjectDto> saveChanges(List<EmployeeSubjectDto> toSave, List<EmployeeSubjectDto> toDelete);

    /**
     * Edits an existing employee-subject relationship.
     *
     * @param dto The DTO of the employee-subject relationship to edit.
     * @return The edited EmployeeSubjectDto.
     */
    EmployeeSubjectDto edit(EmployeeSubjectDto dto);

    /**
     * Finds employee-subject relationships by employee ID.
     *
     * @param id The ID of the employee.
     * @return A list of EmployeeSubjectDto associated with the specified
     * employee ID.
     */
    List<EmployeeSubjectDto> findByEmployee(Long id);

    /**
     * Finds employee-subject relationships by subject ID.
     *
     * @param id The ID of the subject.
     * @return A list of EmployeeSubjectDto associated with the specified
     * subject ID.
     */
    List<EmployeeSubjectDto> findBySubject(Long id);

    /**
     * Deletes an employee-subject relationship.
     *
     * @param Dto  The DTO of the employee-subject relationship to delete.
     */
    void delete(EmployeeSubjectDto Dto);

}
