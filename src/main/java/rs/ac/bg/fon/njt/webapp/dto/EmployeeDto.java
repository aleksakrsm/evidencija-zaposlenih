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
 * Data Transfer Object (DTO) for Employee. This class is used to transfer
 * employee data between processes. It contains information about the employee,
 * including personal details, department, academic title, education title, and
 * status.
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
public class EmployeeDto {

    /**
     * The unique identifier for the employee.
     */
    private Long id;
    /**
     * The firstname of the employee. This field is mandatory and must contain
     * between 3 and 30 characters. It must start with a capital letter and
     * contain only letters.
     */
    @NotNull
    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 3, max = 30, message = "Firstname must contain between 3 and 30 characters")
    @Pattern(regexp = "^[A-Z][a-z]+$",
            message = "Name must start with capital letter and contain only letters. Other letters must be lowercase.")
    private String firstname;
    /**
     * The lastname of the employee. This field is mandatory and must contain
     * between 3 and 30 characters. It must start with a capital letter and
     * contain only letters.
     */
    @NotNull
    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 30, message = "Lastname must contain between 3 and 30 characters")
    @Pattern(regexp = "^[A-Z][a-z]+$",
            message = "Lastname must start with capital letter and contain only letters. Other letters must be lowercase.")
    private String lastname;
    /**
     * The birthday of the employee. This field is mandatory and must be a past
     * date.
     */
    @NotNull(message = "Birthday is mandatory")
    @MyBirthdayValidator
    @Past(message = "Birthday must be in the past")
    private LocalDate birthday;
    /**
     * The department of the employee. This field is mandatory and contains the
     * department information.
     */
    @NotNull(message = "Department is mandatory")
    private DepartmentDto department;
    /**
     * The academic title of the employee. This field is mandatory and contains
     * the academic title information.
     */
    @NotNull(message = "AcademicTitle is mandatory")
    private AcademicTitleDto academicTitle;
    /**
     * The education title of the employee. This field is mandatory and contains
     * the education title information.
     */
    @NotNull(message = "EducationTitle is mandatory")
    private EducationTitleDto educationTitle;
    /**
     * The status of the employee. This field is mandatory and must be validated
     * by the custom validator MyStatusValidator.
     */
    @NotNull(message = "Status iz mandatory")
    @MyStatusValidator
    private Status status;

    /**
     * Constructs an EmployeeDto with the specified details.
     *
     * @param firstname the firstname of the employee
     * @param lastname the lastname of the employee
     * @param birthday the birthday of the employee
     * @param department the department of the employee
     * @param academicTitle the academic title of the employee
     * @param educationTitle the education title of the employee
     * @param status the status of the employee
     */
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
