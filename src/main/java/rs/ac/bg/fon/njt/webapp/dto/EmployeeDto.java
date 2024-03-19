/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.validator.MyBirthdayValidator;
import rs.ac.bg.fon.njt.webapp.validator.MyStatusValidator;

/**
 *
 * @author aleks
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    
    private Long id;
    
    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 3, max = 30, message = "Firstname must contain between 3 and 30 characters")
    @Pattern(regexp = "^[A-Z][a-z]+$",
            message = "Name must start with capital letter and contain only letters. Other letters must be lowercase.")
    private String firstname;
    
    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 30, message = "Lastname must contain between 3 and 30 characters")
    @Pattern(regexp = "^[A-Z][a-z]+$",
            message = "Lastname must start with capital letter and contain only letters. Other letters must be lowercase.")
    private String lastname;
    
    @NotNull(message = "Birthday is mandatory")
    @MyBirthdayValidator
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;
    
    @NotNull(message = "Department is mandatory")
    private DepartmentDto department;
    
    @NotNull(message = "AcademicTitle is mandatory")
    private AcademicTitleDto academicTitle;
    
    @NotNull(message = "EducationTitle is mandatory")
    private EducationTitleDto educationTitle;

    @NotNull(message = "Status iz mandatory")
    @MyStatusValidator
    private Status status;

    public EmployeeDto(String firstname, String lastname, LocalDate birthday, DepartmentDto department, AcademicTitleDto academicTitle, EducationTitleDto educationTitle, Status status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.department = department;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.status = status;
    }

}
