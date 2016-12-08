package com.ruzhi.demo.test.self.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by chunlong.wchl on 2015/6/2.
 * http://blog.csdn.net/ljhabc1982/article/details/18728239
 */
public class SelfNotEmptyValidator implements ConstraintValidator<SelfNotEmpty, String> {

    public void initialize(SelfNotEmpty parameters) {

    }

    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {

        if (string == null){
            return false;
        }else if (string.length() < 1) {
            return false;
        }else{
            return true;
        }

    }

}
