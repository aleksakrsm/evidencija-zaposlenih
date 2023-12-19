/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeAcademicTitleDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeDto;
import rs.ac.bg.fon.njt.webapp.dto.HistoryItemIdDto;

/**
 *
 * @author aleks
 */
public interface EmployeeAcademicTitleService {
    EmployeeAcademicTitleDto save(EmployeeAcademicTitleDto historyItemDto);
    List<EmployeeAcademicTitleDto> saveAll(List<EmployeeAcademicTitleDto> historyItemsDto);
    EmployeeAcademicTitleDto edit(EmployeeAcademicTitleDto historyItemDto);
//    List<EmployeeAcademicTitleDto> findAll();
    EmployeeAcademicTitleDto findById(HistoryItemIdDto idDto);
    List<EmployeeAcademicTitleDto> findByEmployee(EmployeeDto employeeDto);
    void delete(HistoryItemIdDto idDto);
}
