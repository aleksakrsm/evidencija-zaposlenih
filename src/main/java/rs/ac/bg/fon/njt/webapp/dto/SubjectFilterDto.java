/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;

/**
 * Data Transfer Object (DTO) representing a filter for subjects. This class
 * encapsulates filtering criteria for subjects based on their studies type.
 *
 * @author aleks
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubjectFilterDto {

    /**
     * The studies type used as a filter criterion.
     */
    private StudiesType studiesType;
}
