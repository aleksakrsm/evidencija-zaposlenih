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
public class HistoryItemIdDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private static EmployeeDto employeeDto = new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE);
    private static AcademicTitleDto academicTitleDto = new AcademicTitleDto(1l, "Profesor");

    static Stream<HistoryItemIdDto> invalidDtos() {
        return Stream.of(
                new HistoryItemIdDto(null, academicTitleDto, LocalDate.now()),
                new HistoryItemIdDto(employeeDto, null, LocalDate.now()),
                new HistoryItemIdDto(employeeDto, academicTitleDto, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDtos")
    void testHistoryItemIdDtoInvalid(HistoryItemIdDto dto) {
        Assertions.assertFalse(validator.validate(dto).isEmpty());
    }

    @Test
    void testEmployeeSubjectIdPass() {
        HistoryItemIdDto dto = new HistoryItemIdDto();

        dto.setEmployee(employeeDto);
        dto.setAcademicTitle(academicTitleDto);
        dto.setBeginDate(LocalDate.now());
        
        Assertions.assertTrue(validator.validate(dto).isEmpty());
    }
}
