package com.projects.transactions.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/*
 * Custom Annotation to validate Date is not in Past.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransactionTimeValidator.class)
public @interface TransactionTimeToday {
    String message() default "Invalid transaction date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
