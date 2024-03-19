/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubject;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubjectID;

/**
 *
 * @author aleks
 */
@Repository
public interface EmployeeSubjectRepository extends JpaRepository<EmployeeSubject, EmployeeSubjectID>{
    List<EmployeeSubject> findByIdEmployeeId(Long id);
    List<EmployeeSubject> findByIdSubjectId(Long id);
}
