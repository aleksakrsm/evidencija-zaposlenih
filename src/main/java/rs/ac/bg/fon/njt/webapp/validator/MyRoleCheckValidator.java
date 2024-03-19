/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.EnumSet;
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;
import rs.ac.bg.fon.njt.webapp.domain.enums.Role;

/**
 *
 * @author aleks
 */
public class MyRoleCheckValidator implements ConstraintValidator<MyRoleValidator, Role>{

    @Override
    public boolean isValid(Role t, ConstraintValidatorContext cvc) {
        if(t==null)
            return true;
        return EnumSet.allOf(Role.class).stream().anyMatch(value -> value.name().equals(t.name()));
    }
    
}
