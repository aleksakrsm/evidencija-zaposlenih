/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.converter;

import org.mapstruct.Mapper;
import rs.ac.bg.fon.njt.webapp.domain.EmployeeSubjectID;
import rs.ac.bg.fon.njt.webapp.dto.EmployeeSubjectIdDto;

/**
 *
 * @author aleks
 */
@Mapper(componentModel = "spring")
public interface EmployeeSubjectIdMapper {

    EmployeeSubjectID employeeSubjectIdDtoToEmployeeSubjectId(EmployeeSubjectIdDto dto);

    EmployeeSubjectIdDto employeeSubjectIdToEmployeeSubjectIdDto(EmployeeSubjectID id);
    
}
