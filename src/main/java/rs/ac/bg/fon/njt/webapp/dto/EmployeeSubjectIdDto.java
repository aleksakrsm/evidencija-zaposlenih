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

/**
 * Data Transfer Object (DTO) representing the composite ID for the relationship
 * between an employee and a subject. This class contains employee and subject
 * involved in the relationship.
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
public class EmployeeSubjectIdDto {

    /**
     * The employee involved in the relationship. This field is mandatory.
     *
     * @see EmployeeDto
     */
    @NotNull(message = "employee je obavezno polje")
    private EmployeeDto employee;
    /**
     * The subject involved in the relationship. This field is mandatory.
     *
     * @see SubjectDto
     */
    @NotNull(message = "subject je obavezno polje")
    private SubjectDto subject;

}
