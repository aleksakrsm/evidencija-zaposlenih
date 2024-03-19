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
 *
 * @author aleks
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeFilterDto {
    private Long academicTitleId;
    private Long educationTitleId;
    private Long departmentId;
    private Long subjectId;
    @Enumerated(EnumType.STRING)
    private Status status;
}
