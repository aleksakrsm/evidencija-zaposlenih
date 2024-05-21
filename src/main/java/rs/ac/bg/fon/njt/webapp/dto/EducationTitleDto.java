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
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationTitleDto {

    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must contain between 3 and 30 characters")
    @Pattern(regexp = "^(?:(?=\\S)(?![ ])[A-Za-z]{1,29}(?:[ ]|$)){1,}$",
            message = "Name must contain only letters.")
    private String name;

    public EducationTitleDto(String name) {
        this.name = name;
    }
    
}
