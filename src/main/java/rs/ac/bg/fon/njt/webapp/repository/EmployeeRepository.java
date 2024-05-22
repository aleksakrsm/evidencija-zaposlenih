/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;

/**
 * Repository interface for accessing and managing Employee entities in the
 * database.
 *
 * @author aleks
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    /**
     * Finds all Employees based on the given Specification.
     *
     * @param specification The Specification to filter Employees.
     * @return A list of Employees matching the Specification.
     */
    List<Employee> findAll(Specification<Employee> specification);

    /**
     * Finds Employees by their first name.
     *
     * @param firstname The first name of the Employees to find.
     * @return A list of Employees with the specified first name.
     */
    List<Employee> findByFirstname(String firstname);

    /**
     * Finds Employees by their last name.
     *
     * @param lastname The last name of the Employees to find.
     * @return A list of Employees with the specified last name.
     */
    List<Employee> findByLastname(String lastname);

    /**
     * Finds Employees by their department ID.
     *
     * @param id The ID of the department.
     * @return A list of Employees associated with the specified department ID.
     */
    List<Employee> findByDepartmentId(Long id);

    /**
     * Finds Employees by their academic title ID.
     *
     * @param id The ID of the academic title.
     * @return A list of Employees associated with the specified academic title
     * ID.
     */
    List<Employee> findByAcademicTitleId(Long id);

    /**
     * Finds Employees by their education title ID.
     *
     * @param id The ID of the education title.
     * @return A list of Employees associated with the specified education title
     * ID.
     */
    List<Employee> findByEducationTitleId(Long id);

    /**
     * Finds Employees by their status.
     *
     * @param status The status of the Employees to find.
     * @return A list of Employees with the specified status.
     */
    List<Employee> findByStatus(Status status);
}
