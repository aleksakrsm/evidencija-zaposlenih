/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.converter;

import org.mapstruct.Mapper;
import rs.ac.bg.fon.njt.webapp.domain.EducationTitle;
import rs.ac.bg.fon.njt.webapp.dto.EducationTitleDto;

/**
 *
 * @author aleks
 */
@Mapper(componentModel = "spring")
public interface EducationTitleMapper {

    EducationTitleDto educationTitleToEducationTitleDto(EducationTitle educationTitle);

    EducationTitle educationTitleDtoToEducationTitle(EducationTitleDto educationTitleDto);
    
    

}
