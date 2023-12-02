package com.example.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class NumberValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return double.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
            double d=(Double) o;


    }
}
