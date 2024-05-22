/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Data Transfer Object (DTO) representing the composite ID for the relationship
 * between employee and academic title. This class contains employee and
 * academic title involved in the relationship.
 *
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoryItemIdDto {

    /**
     * The employee involved in the relationship. This field is mandatory.
     *
     * @see EmployeeDto
     */
    @NotNull(message = "employee je obavezno polje")
    private EmployeeDto employee;
    /**
     * The academic title involved in the relationship. This field is mandatory.
     *
     * @see AcademicTitleDto
     */
    @NotNull(message = "akademska titula je obavezno polje")
    private AcademicTitleDto academicTitle;

    /**
     * The begining date of academic title. This field is mandatory.
     *
     * @see AcademicTitleDto
     */
    @NotNull(message = "beginDate je obavezno polje")
    private LocalDate beginDate;
}
