package com.ruzhi.demo.test.self.validation;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

//其中@SupportedValidationTarget(ValidationTarget.PARAMETERS)表示验证参数； value将是参数列表。
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class MyCrossParameterValidator implements ConstraintValidator<MyCrossParameter, Object[]> {

    public void initialize(MyCrossParameter constraintAnnotation) {

    }

    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        if(value == null || value.length != 2) {
            throw new IllegalArgumentException("must have two args");
        }
        if(value[0] == null || value[1] == null) {
            return true;
        }
        if(value[0].equals(value[1])) {
            return true;
        }
        return false;
    }
}
