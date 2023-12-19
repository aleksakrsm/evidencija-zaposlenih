/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.DepartmentDto;

/**
 *
 * @author aleks
 */
public interface DepartmentService {
    
    DepartmentDto save(DepartmentDto departmentDto);
    DepartmentDto edit(DepartmentDto departmentDto);
    List<DepartmentDto> findAll();
    DepartmentDto findById(Long id);
    DepartmentDto findByName(String name);
    DepartmentDto findByShortName(String shortName);
    void delete(Long id);
    
}
