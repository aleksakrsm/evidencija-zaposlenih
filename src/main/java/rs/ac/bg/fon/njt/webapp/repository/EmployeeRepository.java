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
 *
 * @author aleks
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long>,JpaSpecificationExecutor<Employee>{
    List<Employee> findAll(Specification<Employee> specification);
    List<Employee> findByFirstname(String firstname);
    List<Employee> findByLastname(String lastname);
    List<Employee> findByDepartmentId(Long id);
    List<Employee> findByAcademicTitleId(Long id);
    List<Employee> findByEducationTitleId(Long id);
    List<Employee> findByStatus(Status status);    
}
