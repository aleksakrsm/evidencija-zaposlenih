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
import rs.ac.bg.fon.njt.webapp.domain.Status;
import rs.ac.bg.fon.njt.webapp.validator.MyBirthdayValidator;
import rs.ac.bg.fon.njt.webapp.validator.MyStatusValidator;

/**
 *
 * @author aleks
 */
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

    public EmployeeDto() {
    }

    public EmployeeDto(String firstname, String lastname, LocalDate birthday, DepartmentDto department, AcademicTitleDto academicTitle, EducationTitleDto educationTitle, Status status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.department = department;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.status = status;
    }

    public EmployeeDto(Long id, String firstname, String lastname, LocalDate birthday, DepartmentDto department, AcademicTitleDto academicTitle, EducationTitleDto educationTitle, Status status) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.department = department;
        this.academicTitle = academicTitle;
        this.educationTitle = educationTitle;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public DepartmentDto getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDto department) {
        this.department = department;
    }

    public AcademicTitleDto getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle(AcademicTitleDto academicTitle) {
        this.academicTitle = academicTitle;
    }

    public EducationTitleDto getEducationTitle() {
        return educationTitle;
    }

    public void setEducationTitle(EducationTitleDto educationTitle) {
        this.educationTitle = educationTitle;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
