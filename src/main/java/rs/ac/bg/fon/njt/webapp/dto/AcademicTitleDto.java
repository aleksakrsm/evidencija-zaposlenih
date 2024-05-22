/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Data Transfer Object (DTO) for AcademicTitle. This class is used to transfer
 * academic title data between processes.
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
public class AcademicTitleDto {

    /**
     * The unique identifier of the academic title.
     */
    private Long id;
    /**
     * The name of the academic title. It must be between 3 and 30 characters
     * long, and only the first letter of each word may be uppercase.
     */
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must contain between 3 and 30 characters")
    @Pattern(regexp = "^(?:(?=\\S)(?![ ])[A-Z]?[a-z]{1,29}(?:[ ]|$)){1,}$",
            message = "Name must contain only letters. Only the first letter of each word may be uppercase")
    private String name;

    /**
     * Constructs a new AcademicTitleDto with the specified name.
     *
     * @param name the name of the academic title
     */
    public AcademicTitleDto(String name) {
        this.name = name;
    }

}
