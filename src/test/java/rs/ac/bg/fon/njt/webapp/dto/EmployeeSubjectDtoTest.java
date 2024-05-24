/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.time.Month;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rs.ac.bg.fon.njt.webapp.domain.enums.ClassType;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;

/**
 *
 * @author aleks
 */
public class EmployeeSubjectDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testPass() {
        EmployeeSubjectIdDto id = new EmployeeSubjectIdDto();
        id.setEmployee(new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE));
        id.setSubject(new SubjectDto(1l, "Njt", 3, StudiesType.MASTER));
        Assertions.assertTrue(validator.validate(new EmployeeSubjectDto(id, ClassType.LECTURES)).isEmpty());
    }

    @Test
    void testFail() {
        Assertions.assertFalse(validator.validate(new EmployeeSubjectDto(null, ClassType.LECTURES)).isEmpty());
    }

}
