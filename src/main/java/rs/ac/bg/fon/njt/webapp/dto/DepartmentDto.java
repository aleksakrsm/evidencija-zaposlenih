/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Data Transfer Object (DTO) for Department. This class is used to transfer
 * department data between processes.
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
public class DepartmentDto {

    /**
     * The unique identifier of the department.
     */
    private Long id;
    /**
     * The name of the department. It must be between 3 and 100 characters long,
     * and only the first letter of each word may be uppercase.
     */
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must contain between 3 and 100 characters")
    @Pattern(regexp = "^(?:(?=\\S)(?![ ])[A-Z]?[a-z]{1,29}(?:[ ]|$)){1,}$",
            message = "Name must contain only letters. Only the first letter of each word may be uppercase")
    private String name;
    /**
     * The short name of the department. It must be between 2 and 20 characters
     * long, and consist of uppercase letters only.
     */
    @NotBlank(message = "Short name is mandatory")
    @Size(min = 2, max = 20, message = "Short name must contain between 2 and 20 characters")
    @Pattern(regexp = "^[A-Z]+$",
            message = "Short name must be single word consisting of uppercase letters only")
    private String shortName;

    /**
     * Constructs a new DepartmentDto with the specified name and short name.
     *
     * @param name the name of the department
     * @param shortName the short name of the department
     */
    public DepartmentDto(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

}
