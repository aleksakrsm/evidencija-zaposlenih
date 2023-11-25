/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.converter;

import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.Mapping;
import rs.ac.bg.fon.njt.webapp.domain.AcademicTitle;
import rs.ac.bg.fon.njt.webapp.dto.AcademicTitleDto;

/**
 *
 * @author aleks
 */
@Mapper(componentModel = "spring")
public interface AcademicTitleMapper {
    
    AcademicTitleDto academicTitleToAcademicTitleDto(AcademicTitle academicTitle);

    AcademicTitle academicTitleDtoToAcademicTitle(AcademicTitleDto academicTitleDto);

}
