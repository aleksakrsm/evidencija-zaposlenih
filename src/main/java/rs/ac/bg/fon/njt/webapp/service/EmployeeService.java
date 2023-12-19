/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.domain.Status;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;

/**
 *
 * @author aleks
 */
public interface EmployeeService {
    
    EmployeeDto save(EmployeeDto employeeDto);
    EmployeeDto edit(EmployeeDto employeeDto);
    List<EmployeeDto> findAll();
    EmployeeDto findById(Long id);
//    List<EmployeeDto> findByFirstname(String firstname);
//    List<EmployeeDto> findByLastname(String lastname);
//    List<EmployeeDto> findByAcademicTitle(AcademicTitleDto academicTitleDto);
//    List<EmployeeDto> findByEducationTitle(EducationTitleDto educationTitleDto);
    List<EmployeeDto> findByDepartmentDto(DepartmentDto departmentDto);
    List<EmployeeDto> findByDepartmentId(Long departmentId);
    List<EmployeeDto> findByStatus(Status status);
    void delete(Long id);
    
}
