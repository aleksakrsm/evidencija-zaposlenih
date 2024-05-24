/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.dto;

import jakarta.validation.Validator;
import jakarta.validation.Validation;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;

/**
 *
 * @author aleks
 */
public class SubjectDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    
    static Stream<SubjectDto> invalidSubjectDtos() {
        return Stream.of(
                new SubjectDto(1l, null, 3, StudiesType.MASTER),
                new SubjectDto(1l, "", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Nj", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Njjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj", 3, StudiesType.MASTER),
                new SubjectDto(1l, "NjT", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Nj2", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Njt Njt2", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Njt NjT", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Njt", 0, StudiesType.MASTER),
                new SubjectDto(1l, "Njt", 20, StudiesType.MASTER),
                new SubjectDto(1l, "Matematika2", 3, StudiesType.MASTER)
        );
    }
    static Stream<SubjectDto> validSubjectDtos() {
        return Stream.of(
                new SubjectDto(1l, "Njt", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Napredne Java Tehnologije", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Matematika i muzika", 3, StudiesType.MASTER),
                new SubjectDto(1l, "Matematika 2", 3, StudiesType.MASTER)
        );
    }
    
    @ParameterizedTest
    @MethodSource("invalidSubjectDtos")
    void testSubjectDtoInvalid(SubjectDto dto) {
        Assertions.assertFalse(validator.validate(dto).isEmpty());
    }
    @ParameterizedTest
    @MethodSource("validSubjectDtos")
    void testSubjectDtoValid(SubjectDto dto) {
        Assertions.assertTrue(validator.validate(dto).isEmpty());
    }
    
}
