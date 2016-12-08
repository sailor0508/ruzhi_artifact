package com.ruzhi.demo.test.self.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {SelfNotEmptyValidator.class})
public @interface SelfNotEmpty {//注解声明，类似于切面的声明

    String message() default "this string may be empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}