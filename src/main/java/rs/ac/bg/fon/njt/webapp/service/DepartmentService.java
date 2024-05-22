/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;

/**
 * Service interface for managing departments. Provides methods for saving,
 * editing, finding, and deleting departments.
 *
 * @see DepartmentDto
 *
 * This interface defines the contract for department-related operations.
 * Implementations are responsible for the actual logic of these operations.
 *
 * Note: This interface is typically implemented by a service class that
 * interacts with the data layer.
 *
 * @author aleks
 */
public interface DepartmentService {

    /**
     * Saves a new department.
     *
     * @param departmentDto The DTO of the department to save.
     * @return The saved DepartmentDto.
     */
    DepartmentDto save(DepartmentDto departmentDto);

    /**
     * Edits an existing department.
     *
     * @param departmentDto The DTO of the department to edit.
     * @return The edited DepartmentDto.
     */
    DepartmentDto edit(DepartmentDto departmentDto);

    /**
     * Finds all departments.
     *
     * @return A list of DepartmentDto.
     */
    List<DepartmentDto> findAll();

    /**
     * Finds a department by its ID.
     *
     * @param id The ID of the department.
     * @return The DepartmentDto with the given ID.
     */
    DepartmentDto findById(Long id);

    /**
     * Finds a department by its name.
     *
     * @param name The name of the department.
     * @return The DepartmentDto with the given name.
     */
    DepartmentDto findByName(String name);

    /**
     * Finds a department by its short name.
     *
     * @param shortName The short name of the department.
     * @return The DepartmentDto with the given short name.
     */
    DepartmentDto findByShortName(String shortName);

    /**
     * Deletes a department by its ID.
     *
     * @param id The ID of the department to delete.
     */
    void delete(Long id);

}
