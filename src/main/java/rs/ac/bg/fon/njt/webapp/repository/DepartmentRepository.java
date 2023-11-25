/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.njt.webapp.domain.Department;

/**
 *
 * @author aleks
 */
public interface DepartmentRepository extends JpaRepository<Department, Long>{
    Optional<Department> findByName(String name);
    Optional<Department> findByShortName(String shortName);
}
