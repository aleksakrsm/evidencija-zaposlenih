/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.njt.webapp.domain.enums.ClassType;
import rs.ac.bg.fon.njt.webapp.validator.MyClassTypeValidator;

/**
 *
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSubjectDto {
    
    @NotNull(message = "id je obavezno polje")
    private EmployeeSubjectIdDto id;
    
    @MyClassTypeValidator
    private ClassType classType;
    
}
