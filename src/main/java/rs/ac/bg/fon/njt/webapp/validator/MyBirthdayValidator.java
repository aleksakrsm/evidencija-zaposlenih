/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package rs.ac.bg.fon.njt.webapp.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom constraint annotation to validate if a date represents a valid
 * birthday. A valid birthday should be on or before the current date and not
 * more than 100 years ago.
 *
 * @author aleks
 */
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyBirthdayCheckValidator.class)
@Documented
public @interface MyBirthdayValidator {

    /**
     * Default message to be shown when validation fails.
     *
     * @return The default error message.
     */
    String message() default "Date myst be in the past and not more than 100y ago";

    /**
     * Groups that can be used to restrict the constraints applied.
     *
     * @return The array of constraint groups.
     */
    Class<?>[] groups() default {};

    /**
     * Additional metadata information about the validation payload.
     *
     * @return The array of payload classes.
     */
    Class<? extends Payload>[] payload() default {};
}
