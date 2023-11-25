/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author aleks
 */
public class EducationTitleDto {

    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 30, message = "Name must contain between 3 and 30 characters")// proveriti za titulesa dve reci
//    @Pattern(regexp = "([a-z]{3,30}+)|([A-Z][a-z]{2,30}+)", message = "Name must contain only letters. Only first letter may be uppercase")
    @Pattern(regexp = "^(?:(?=\\S)(?![ ])[A-Za-z]{3,30}(?:[ ]|$)){1,}$",
            message = "Name must contain only letters. Only the first letter of words may be uppercase")
    private String name;

    public EducationTitleDto() {
    }

    public EducationTitleDto(String name) {
        this.name = name;
    }

    public EducationTitleDto(Long id, String name) {
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
