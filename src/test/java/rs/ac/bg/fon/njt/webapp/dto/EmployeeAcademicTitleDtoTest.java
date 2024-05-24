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
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;

/**
 *
 * @author aleks
 */
public class EmployeeAcademicTitleDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void testPass() {
        HistoryItemIdDto dtoID = new HistoryItemIdDto();
        dtoID.setEmployee(new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE)
        );
        dtoID.setAcademicTitle(new AcademicTitleDto(1l, "Profesor"));
        dtoID.setBeginDate(LocalDate.of(2024, Month.MARCH, 3));
        Assertions.assertTrue(validator.validate(new EmployeeAcademicTitleDto(dtoID, LocalDate.now())).isEmpty());
    }

    @Test
    void testFail() {
        Assertions.assertFalse(validator.validate(new EmployeeAcademicTitleDto(null, LocalDate.now())).isEmpty());
    }

}
