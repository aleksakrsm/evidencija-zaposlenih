/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 *
 * @author aleks
 */
public class AcademicTiteDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    static Stream<AcademicTitleDto> invalidAcademicTitleDtos() {
        return Stream.of(
                new AcademicTitleDto(1l, null),
                new AcademicTitleDto(1l, ""),
                new AcademicTitleDto(1l, "Aa"),
                new AcademicTitleDto(1l, "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"),
                new AcademicTitleDto(1l, "Akademska TitUla"),
                new AcademicTitleDto(1l, "Akademska 1"),
                new AcademicTitleDto(1l, "Akademska t1tula")
        );
    }
    static Stream<AcademicTitleDto> validAcademicTitleDtos() {
        return Stream.of(
                new AcademicTitleDto(null, "Profesor"),
                new AcademicTitleDto(1l, "Profesor Redovni"),
                new AcademicTitleDto(1l, "Profesor redovni")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidAcademicTitleDtos")
    void testAcademicTitleDtoInvalid(AcademicTitleDto dto) {
        Assertions.assertFalse(validator.validate(dto).isEmpty());
    }
    @ParameterizedTest
    @MethodSource("validAcademicTitleDtos")
    void testAcademicTitleDtoValid(AcademicTitleDto dto) {
        Assertions.assertTrue(validator.validate(dto).isEmpty());
    }

}
