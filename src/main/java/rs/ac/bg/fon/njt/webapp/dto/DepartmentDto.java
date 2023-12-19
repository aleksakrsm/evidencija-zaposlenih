/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
//import jakarta.validation.constraints.;

/**
 *
 * @author aleks
 */
public class DepartmentDto {
    
    private Long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 3, max = 100, message = "Name must contain between 3 and 100 characters")
    @Pattern(regexp = "^(?:(?=\\S)(?![ ])[A-Z]?[a-z]{1,29}(?:[ ]|$)){1,}$",
            message = "Name must contain only letters. Only the first letter of each word may be uppercase")
    private String name;
    @NotBlank(message = "Short name is mandatory")
    @Size(min = 2, max = 20, message = "Short name must contain between 2 and 20 characters")
    @Pattern(regexp = "^[A-Z]+$",
            message = "Short name must be single word consisting of uppercase letters only")
    private String shortName;

    public DepartmentDto() {
    }

    public DepartmentDto(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public DepartmentDto(Long id, String name, String shortName) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
