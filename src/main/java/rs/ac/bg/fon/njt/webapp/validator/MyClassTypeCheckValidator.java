/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import lombok.NoArgsConstructor;
import rs.ac.bg.fon.njt.webapp.domain.enums.ClassType;
import rs.ac.bg.fon.njt.webapp.domain.enums.StudiesType;

/**
 *
 * @author aleks
 */
@NoArgsConstructor
public class MyClassTypeCheckValidator implements ConstraintValidator<MyClassTypeValidator, ClassType> {

    @Override
    public boolean isValid(ClassType t, ConstraintValidatorContext cvc) {
        if (t == null) {
            return true;
        }
        return EnumSet.allOf(ClassType.class).stream().anyMatch(value -> value.name().equals(t.name()));
    }

}
