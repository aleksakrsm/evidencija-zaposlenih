/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.Department;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.domain.Employee;
import rs.ac.bg.fon.njt.webapp.domain.Status;


/**
 *
 * @author aleks
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    List<Employee> findByFirstname(String firstname);
    List<Employee> findByLastname(String lastname);
    List<Employee> findByDepartmentId(Long id);
    List<Employee> findByAcademicTitleId(Long id);
    List<Employee> findByEducationTitleId(Long id);
    List<Employee> findByStatus(Status status);    
}
