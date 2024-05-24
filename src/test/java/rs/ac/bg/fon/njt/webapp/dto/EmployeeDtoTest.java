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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;

/**
 *
 * @author aleks
 */
public class EmployeeDtoTest {
        private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    static Stream<EmployeeDto> invalidEmployeeDtos() {
        return Stream.of(
               new EmployeeDto(1l, null, "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Al", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "AlE", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Ale1", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksaaaaaaleksaaaaaaleksaaaaaar", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
              
               new EmployeeDto(1l, "Aleksa", null, LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksa", "", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksa", "Kr", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksa", "KrS", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksa", "Krs1", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksa", "Krsmanovicrsmanovicrsmanovicrsmanovic", LocalDate.of(21, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", null, new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1800, Month.MARCH, 1), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(2124, Month.MARCH, 1), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), null, new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), null, new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), null, Status.ACTIVE),
               
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), null)
        );
    }
    static Stream<EmployeeDto> validEmployeeDtos() {
        return Stream.of(
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.ACTIVE),
               new EmployeeDto(1l, "Aleksa", "Krsmanovic", LocalDate.of(1999, Month.JUNE, 21), new DepartmentDto(1l, "Silab", "SILAB"), new AcademicTitleDto(1l, "Profesor"), new EducationTitleDto(1l, "PhD"), Status.INACTIVE)
        );
    }
    
    @ParameterizedTest
    @MethodSource("invalidEmployeeDtos")
    void testEmployeeDtoInvalid(EmployeeDto dto) {
        Assertions.assertFalse(validator.validate(dto).isEmpty());
    }
    @ParameterizedTest
    @MethodSource("validEmployeeDtos")
    void testEmployeeDtoValid(EmployeeDto dto) {
        Assertions.assertTrue(validator.validate(dto).isEmpty());
    }
}
