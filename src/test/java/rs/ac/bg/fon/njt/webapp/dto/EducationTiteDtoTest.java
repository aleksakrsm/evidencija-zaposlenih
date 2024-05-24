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
public class EducationTiteDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    static Stream<EducationTitleDto> invalidEducationTitleDtos() {
        return Stream.of(
                new EducationTitleDto(1l, null),
                new EducationTitleDto(1l, ""),
                new EducationTitleDto(1l, "Aa"),
                new EducationTitleDto(1l, "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"),
                new EducationTitleDto(1l, "PhD1"),
                new EducationTitleDto(1l, "PhD 1"),
                new EducationTitleDto(1l, "Doktor Nau1ka")
        );
    }
    static Stream<EducationTitleDto> validEducationTitleDtos() {
        return Stream.of(
                new EducationTitleDto(null, "PhD"),
                new EducationTitleDto(1l, "Doktor Nauka"),
                new EducationTitleDto(1l, "Doktor nauka"),
                new EducationTitleDto(1l, "MSc"),
                new EducationTitleDto(1l, "BSc")
        );
    }

    @ParameterizedTest
    @MethodSource("invalidEducationTitleDtos")
    void testEducationTitleDtoInvalid(EducationTitleDto dto) {
        Assertions.assertFalse(validator.validate(dto).isEmpty());
    }
    @ParameterizedTest
    @MethodSource("validEducationTitleDtos")
    void testEducationTitleDtoValid(EducationTitleDto dto) {
        Assertions.assertTrue(validator.validate(dto).isEmpty());
    }

}
