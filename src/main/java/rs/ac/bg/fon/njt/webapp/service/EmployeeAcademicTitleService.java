/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeAcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.HistoryItemIdDto;

/**
 * Service interface for managing employee academic titles. Provides methods for
 * saving, editing, finding, and deleting employee academic titles.
 *
 * @see EmployeeAcademicTitleDto
 * @see HistoryItemIdDto
 * @see EmployeeDto
 *
 * This interface defines the contract for employee academic title-related
 * operations. Implementations are responsible for the actual logic of these
 * operations. The methods allow for both single and batch operations, as well
 * as specialized operations like saving changes and finding by employee.
 * @author aleks
 */
public interface EmployeeAcademicTitleService {

    /**
     * Saves a new employee academic title.
     *
     * @param historyItemDto The DTO of the employee academic title to save.
     * @return The saved EmployeeAcademicTitleDto.
     */
    EmployeeAcademicTitleDto save(EmployeeAcademicTitleDto historyItemDto);

    /**
     * Saves multiple employee academic titles.
     *
     * @param historyItemsDto The list of DTOs of the employee academic titles
     * to save.
     * @return The list of saved EmployeeAcademicTitleDto.
     */
    List<EmployeeAcademicTitleDto> saveAll(List<EmployeeAcademicTitleDto> historyItemsDto);

    /**
     * Saves changes to multiple employee academic titles, including deleting
     * specified items.
     *
     * @param historyItemsDto The list of DTOs of the employee academic titles
     * to save.
     * @param deleteItems The list of DTOs of the employee academic titles to
     * delete.
     * @return The list of saved EmployeeAcademicTitleDto.
     */
    List<EmployeeAcademicTitleDto> saveChanges(List<EmployeeAcademicTitleDto> historyItemsDto, List<EmployeeAcademicTitleDto> deleteItems);

    /**
     * Edits an existing employee academic title.
     *
     * @param historyItemDto The DTO of the employee academic title to edit.
     * @return The edited EmployeeAcademicTitleDto.
     */
    EmployeeAcademicTitleDto edit(EmployeeAcademicTitleDto historyItemDto);

    /**
     * Finds an employee academic title by its ID.
     *
     * @param idDto The DTO of the ID of the employee academic title.
     * @return The found EmployeeAcademicTitleDto.
     */
    EmployeeAcademicTitleDto findById(HistoryItemIdDto idDto);

    /**
     * Finds employee academic titles by employee DTO.
     *
     * @param employeeDto The DTO of the employee.
     * @return A list of EmployeeAcademicTitleDto associated with the specified
     * employee.
     */
    List<EmployeeAcademicTitleDto> findByEmployee(EmployeeDto employeeDto);

    /**
     * Finds employee academic titles by employee ID.
     *
     * @param empId The ID of the employee.
     * @return A list of EmployeeAcademicTitleDto associated with the specified
     * employee.
     */
    List<EmployeeAcademicTitleDto> findByEmployee(Long empId);

    /**
     * Deletes an employee academic title by its ID.
     *
     * @param idDto The DTO of the ID of the employee academic title to delete.
     */
    void delete(HistoryItemIdDto idDto);
}
