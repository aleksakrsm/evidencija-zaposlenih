/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;

/**
 * Data Transfer Object (DTO) for filtering employee search results. This class
 * is used to specify filter criteria when searching for employees. It contains
 * filter fields such as academic title ID, education title ID, department ID,
 * subject ID, and status.
 *
 * <p>
 * Uses Lombok annotations for boilerplate code reduction:
 * <ul>
 * <li>{@link Data} generates getters, setters, and other utility methods.
 * <li>{@link NoArgsConstructor} generates a no-args constructor.
 * <li>{@link AllArgsConstructor} generates an all-args constructor.
 * </ul>
 *
 * @author aleks
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeFilterDto {

    /**
     * The ID of the academic title to filter employees by.
     * Can be null to indicate no filtering by academic title.
     */
    private Long academicTitleId;
    
    /**
     * The ID of the education title to filter employees by.
     * Can be null to indicate no filtering by education title.
     */
    private Long educationTitleId;
    
    /**
     * The ID of the department to filter employees by.
     * Can be null to indicate no filtering by department.
     */
    private Long departmentId;
    
    /**
     * The ID of the subject to filter employees by.
     * Can be null to indicate no filtering by subject.
     */
    private Long subjectId;
    
    /**
     * The status to filter employees by.
     * Uses the {@link Status} enum.
     * Can be null to indicate no filtering by status.
     */
    @Enumerated(EnumType.STRING)
    private Status status;
}
