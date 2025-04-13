package com.projects.transactions.validators;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class  TransactionTimeValidator implements ConstraintValidator<TransactionTimeToday, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        if(value == null) {
            return false;
        }
        LocalDate currDate = value.atZone(ZoneId.systemDefault()).toLocalDate();
        return currDate.isBefore(LocalDate.now()) ? false : true;
    }
}
