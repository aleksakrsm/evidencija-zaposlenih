/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author aleks
 */
public class AcademicTitleDto {

    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must contain between 3 and 30 characters")
    @Pattern(regexp = "^(?:(?=\\S)(?![ ])[A-Z]?[a-z]{1,29}(?:[ ]|$)){1,}$",
            message = "Name must contain only letters. Only the first letter of each word may be uppercase")
    private String name;

    public AcademicTitleDto() {
    }

    public AcademicTitleDto(String name) {
        this.name = name;
    }

    public AcademicTitleDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
