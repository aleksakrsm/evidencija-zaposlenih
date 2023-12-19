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
//import java.lang.annotation.;

/**
 *
 * @author aleks
 */
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR,ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyBirthdayCheckValidator.class)
@Documented
public @interface MyBirthdayValidator {
    String message() default "Date myst be in the past and not more than 100y ago";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
