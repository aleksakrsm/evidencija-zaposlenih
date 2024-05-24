/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import lombok.NoArgsConstructor;

/**
 * Validates if a given LocalDate represents a valid birthday. A valid birthday
 * should be on or before the current date and not more than 100 years ago.
 *
 * @author aleks
 */
@NoArgsConstructor
public class MyBirthdayCheckValidator implements ConstraintValidator<MyBirthdayValidator, LocalDate> {

    /**
     * Checks if the given LocalDate is a valid birthday.
     *
     * @param date The LocalDate to be validated.
     * @param context The validation context.
     * @return {@code true} if the LocalDate is a valid birthday, {@code false}
     * otherwise.
     */
    @Override
    public boolean isValid(LocalDate t, ConstraintValidatorContext cvc) {
        if (t == null) {
            return true;
        }
        LocalDate currentDate = LocalDate.now();
        LocalDate hundredYearsAgo = currentDate.minusYears(100);

        return !t.isAfter(currentDate) && !t.isBefore(hundredYearsAgo);
    }

}
