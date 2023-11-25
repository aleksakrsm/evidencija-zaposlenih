/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeAcademicTitle;
import rs.ac.bg.fon.njt.webapp.domain.HistoryItemID;

/**
 *
 * @author aleks
 */
@Repository
public interface EmployeeAcademicTitleRepository extends JpaRepository<EmployeeAcademicTitle, HistoryItemID>{
    
}
