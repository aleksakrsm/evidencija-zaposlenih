/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeFilterDto;

/**
 * Service interface for managing employees. Provides methods for saving,
 * editing, finding, filtering, and deleting employees.
 *
 * @see EmployeeDto
 * @see EmployeeFilterDto
 * @see DepartmentDto
 * @see Status
 *
 * This interface defines the contract for employee-related operations.
 * Implementations are responsible for the actual logic of these operations.
 *
 * @author aleks
 */
public interface EmployeeService {

    /**
     * Saves a new employee.
     *
     * @param employeeDto The DTO of the employee to save.
     * @return The saved EmployeeDto.
     */
    EmployeeDto save(EmployeeDto employeeDto);

    /**
     * Edits an existing employee.
     *
     * @param employeeDto The DTO of the employee to edit.
     * @return The edited EmployeeDto.
     */
    EmployeeDto edit(EmployeeDto employeeDto);

    /**
     * Finds all employees.
     *
     * @return A list of EmployeeDto.
     */
    List<EmployeeDto> findAll();

    /**
     * Finds all employees with pagination.
     *
     * @param pageable The pagination information.
     * @return A list of EmployeeDto.
     */
    List<EmployeeDto> findAll(Pageable pageable);

    /**
     * Filters employees based on the given filter criteria.
     *
     * @param filterDto The filter criteria.
     * @return A list of EmployeeDto matching the filter criteria.
     */
    List<EmployeeDto> filter(EmployeeFilterDto filterDto);

    /**
     * Filters and paginates employees based on the given filter criteria and
     * pagination information.
     *
     * @param filterDto The filter criteria.
     * @param pageable The pagination information.
     * @return A list of EmployeeDto matching the filter criteria and pagination
     * information.
     */
    List<EmployeeDto> filterPaginate(EmployeeFilterDto filterDto, Pageable pageable);

    /**
     * Paginates and filters employees based on the given filter criteria and
     * pagination information.
     *
     * @param filterDto The filter criteria.
     * @param pageable The pagination information.
     * @return A Page of EmployeeDto matching the filter criteria and pagination
     * information.
     */
    Page<EmployeeDto> pageFilterPaginate(EmployeeFilterDto filterDto, Pageable pageable);

    /**
     * Searches employees based on the given search term.
     *
     * @param term The search term.
     * @return A list of EmployeeDto matching the search term.
     */
    List<EmployeeDto> search(String term);

    /**
     * Finds an employee by its ID.
     *
     * @param id The ID of the employee.
     * @return The EmployeeDto with the given ID.
     */
    EmployeeDto findById(Long id);

    /**
     * Finds employees by their department DTO.
     *
     * @param departmentDto The department DTO.
     * @return A list of EmployeeDto associated with the specified department
     * DTO.
     */
    List<EmployeeDto> findByDepartmentDto(DepartmentDto departmentDto);

    /**
     * Finds employees by their department id.
     *
     * @param departmentId  The department id.
     * @return A list of EmployeeDto associated with the specified department
     * DTO.
     */
    List<EmployeeDto> findByDepartmentId(Long departmentId);

    /**
     * Finds employees by their department DTO.
     *
     * @param status  The employee status.
     * @return A list of EmployeeDto with searched status.
     */
    List<EmployeeDto> findByStatus(Status status);

    /**
     * Deletes an employee by its ID.
     *
     * @param id The ID of the employee to delete.
     */
    void delete(Long id);

    /**
     * Counts employees by their academic title ID and department ID.
     *
     * @param academicTitleId The ID of the academic title.
     * @param departmentId The ID of the department.
     * @return The number of employees matching the specified academic title ID
     * and department ID.
     */
    Long countByAcademicTitleAndDepartment(Long academicTitleId, Long departmentId);

}
