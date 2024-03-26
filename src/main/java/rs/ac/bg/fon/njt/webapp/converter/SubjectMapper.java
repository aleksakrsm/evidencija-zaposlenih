/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.converter;

import org.mapstruct.Mapper;
import rs.ac.bg.fon.njt.webapp.domain.Subject;
import rs.ac.bg.fon.njt.webapp.dto.SubjectDto;

/**
 *
 * @author aleks
 */
@Mapper(componentModel = "spring")
public interface SubjectMapper {
    
    SubjectDto subjectToSubjectDto(Subject subject);

    Subject subjectDtoToSubject(SubjectDto dto);

}
