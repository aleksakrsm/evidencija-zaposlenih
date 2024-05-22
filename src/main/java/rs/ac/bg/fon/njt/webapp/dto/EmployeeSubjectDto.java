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
 * Data Transfer Object (DTO) for EmployeeSubject. This class contains
 * information about the employee, the subject employee teaches and class type.
 *
 * <p>
 * Uses Lombok annotations for boilerplate code reduction:
 * <ul>
 * <li>{@link Data} generates getters, setters, and other utility methods.
 * <li>{@link Builder} provides a builder pattern implementation.
 * <li>{@link NoArgsConstructor} generates a no-args constructor.
 * <li>{@link AllArgsConstructor} generates an all-args constructor.
 * </ul>
 *
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeSubjectDto {

    /**
     * The ID of the employee-subject relationship. It is a composite ID
     * consisting of the employee ID and subject ID. This field is mandatory.
     *
     * @see EmployeeSubjectIdDto
     */
    @NotNull(message = "id je obavezno polje")
    private EmployeeSubjectIdDto id;
    /**
     * The class type of the subject for the employee. This field is mandatory.
     *
     * @see ClassType
     * @see MyClassTypeValidator
     */
    @MyClassTypeValidator
    private ClassType classType;

}
