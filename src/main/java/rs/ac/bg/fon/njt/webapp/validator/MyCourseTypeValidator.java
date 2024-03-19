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
import rs.ac.bg.fon.njt.webapp.domain.enums.Status;

/**
 *
 * @author aleks
 */
@Constraint(validatedBy = MyCourseTypeCheckValidator.class)
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyCourseTypeValidator {

    String message() default "Course type can be only ELECTIVE, REQUIRED or ALTERNATIVE";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
