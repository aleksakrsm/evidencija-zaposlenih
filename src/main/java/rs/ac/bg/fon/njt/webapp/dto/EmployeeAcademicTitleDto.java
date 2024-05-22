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
 * Data Transfer Object (DTO) for EmployeeAcademicTitle. This class is used to
 * transfer employee academic title data between processes. It contains
 * information about the employee's academic title and the duration for which
 * the title was held.
 *
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
public class EmployeeAcademicTitleDto {

    /**
     * The composite key for the employee academic title history. It contains
     * details about the employee, academic title, and the start date of the
     * title. This field is mandatory.
     */

    @NotNull(message = "id je obavezno polje")
    private HistoryItemIdDto historyItemIdDto;
    /**
     * The end date of the academic title. This field is optional and represents
     * the date when the employee's academic title ended.
     */
    private LocalDate endDate;

}
