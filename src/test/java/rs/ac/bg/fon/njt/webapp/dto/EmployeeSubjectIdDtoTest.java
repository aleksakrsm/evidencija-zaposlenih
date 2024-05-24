/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.time.LocalDate;
import java.time.Month;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;

/**
 *
 * @author aleks
 */
public class EmployeeSubjectIdDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    static Stream<EmployeeSubjectIdDto> invalidDtos() {
        return Stream.of(
                new EmployeeSubjectIdDto(null, new SubjectDto(1l, "Njt", 3, StudiesType.MASTER)),
                new EmployeeSubjectIdDto(new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE), null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDtos")
    void testEmployeeSubjectIdDtoInvalid(EmployeeSubjectIdDto dto) {
        Assertions.assertFalse(validator.validate(dto).isEmpty());
    }
    @Test
    void testEmployeeSubjectIdPass() {
        EmployeeSubjectIdDto dto = new EmployeeSubjectIdDto();
        dto.setEmployee(new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE));
        dto.setSubject(new SubjectDto(1l, "Njt", 3, StudiesType.MASTER));
        Assertions.assertTrue(validator.validate(dto).isEmpty());
    }
}
