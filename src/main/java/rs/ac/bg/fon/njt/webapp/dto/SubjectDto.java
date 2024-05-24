/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;
import rs.ac.bg.fon.njt.webapp.validator.MyStudiesTypeValidator;

/**
 * Data Transfer Object (DTO) representing a subject. This class encapsulates
 * information about a subject, including its ID, name, ECTS credits, and
 * studies type.
 *
 *
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {

    /**
     * The unique identifier of the subject.
     */
    private Long id;
    /**
     * The name of the subject. Must be between 3 and 60 characters long and
     * contain only letters. Only the first letter of each word may be
     * uppercase.
     */
    @NotNull
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 60, message = "Name must contain between 3 and 60 characters")
//    @Pattern(regexp = "^(?:(?=\\S)(?![ ])[A-Z]?[a-z]{1,29}(?:[ ]|$)){1,}(?:([ ][1-9]))?$",
    @Pattern(regexp = "^[A-Z][a-z]{1,29}(?: [A-Z]?[a-z]{1,29})*(?: [1-9])?$",
            message = "Only the first letter of each word may be uppercase")
    private String name;
    /**
     * The number of ECTS credits assigned to the subject. Must be between 1 and
     * 10.
     */
    @Min(value = 1, message = "Minimal ects value is 1")
    @Max(value = 10, message = "Maximal ects value is 10")
    private int ects;
    /**
     * The type of studies associated with the subject. This field is validated
     * using a custom validator {@link MyStudiesTypeValidator}.
     */
    @MyStudiesTypeValidator
    private StudiesType studiesType;

}
