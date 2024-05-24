/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author aleks
 */
public class DepartmentDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    static Stream<DepartmentDto> invalidDepartmentDtos() {
        return Stream.of(
                new DepartmentDto(1l, null, "SILAB"),
                new DepartmentDto(1l, "", "SILAB"),
                new DepartmentDto(1l, "Si", "SILAB"),
                new DepartmentDto(1l, "Siiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii11111", "SILAB"),
                new DepartmentDto(1l, "Si1lab", "SILAB"),
                new DepartmentDto(1l, "Si.lab", "SILAB"),
                new DepartmentDto(1l, "Silab Sil1ab", "SILAB"),
                new DepartmentDto(1l, "Silab SiLab", "SILAB"),
                new DepartmentDto(1l, "Silab", null),
                new DepartmentDto(1l, "Silab", ""),
                new DepartmentDto(1l, "Silab", "S"),
                new DepartmentDto(1l, "Silab", "SSSSSSSSSSSSSSSSSSSSSSSS"),
                new DepartmentDto(1l, "Silab", "SILAB SILAB"),
                new DepartmentDto(1l, "Silab", "SIL4B"),
                new DepartmentDto(1l, "Silab", "Silab")
                
        );
    }
    static Stream<DepartmentDto> validDepartmentDtos() {
        return Stream.of(
                new DepartmentDto(null, "Silab", "SILAB"),
                new DepartmentDto(1l, "Silab Silab", "SILAB"),
                new DepartmentDto(1l, "Silab za Silab", "SILAB"),
                new DepartmentDto(1l, "Silab i Silab", "SILAB"),
                new DepartmentDto(1l, "Silab silab", "SILAB")
        );
    }
    
    @ParameterizedTest
    @MethodSource("invalidDepartmentDtos")
    void testDepartmentDtoInvalid(DepartmentDto dto) {
        Assertions.assertFalse(validator.validate(dto).isEmpty());
    }
    @ParameterizedTest
    @MethodSource("validDepartmentDtos")
    void testDepartmentDtoValid(DepartmentDto dto) {
        Assertions.assertTrue(validator.validate(dto).isEmpty());
    }

}
