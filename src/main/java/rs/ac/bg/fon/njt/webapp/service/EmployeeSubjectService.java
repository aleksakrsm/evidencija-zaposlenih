/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.service;

import java.util.List;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectDto;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectIdDto;

/**
 *
 * @author aleks
 */
public interface EmployeeSubjectService {

    EmployeeSubjectDto save(EmployeeSubjectDto dto);

//    List<EmployeeSubjectDto> saveAll(List<EmployeeSubjectDto> list);

    EmployeeSubjectDto edit(EmployeeSubjectDto dto);

    List<EmployeeSubjectDto> findByEmployee(Long id);

    List<EmployeeSubjectDto> findBySubject(Long id);

    void delete(EmployeeSubjectDto Dto);

}
