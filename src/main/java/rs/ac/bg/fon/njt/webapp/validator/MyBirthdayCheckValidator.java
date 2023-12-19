/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 *
 * @author aleks
 */
class MyBirthdayCheckValidator implements ConstraintValidator<MyBirthdayValidator, LocalDate>{

    @Override
    public boolean isValid(LocalDate t, ConstraintValidatorContext cvc) {
        if(t==null)
            return true;
        LocalDate currentDate = LocalDate.now();
        LocalDate hundredYearsAgo = currentDate.minusYears(100);

        return !t.isAfter(currentDate) && !t.isBefore(hundredYearsAgo);
    }
    
}
