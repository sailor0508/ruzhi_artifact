package com.ruzhi.demo.test.self.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public class MyPatternOfStringValidator implements ConstraintValidator<MyPatternOfString, String> {

    private String letterIn;

    public void initialize(MyPatternOfString parameters) {
        this.letterIn = parameters.mustContainLetter();
    }

    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (string.contains(letterIn)){
            return true;
        }
        return false;
    }
}
