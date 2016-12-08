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
@Constraint(validatedBy = MyPatternOfStringValidator.class)

public @interface MyPatternOfString {

    String mustContainLetter();//这里省略了default

    String message() default "thispattern may not be right";

    Class<?>[] groups() default { };

    Class<? extends Payload>[]payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        MyPatternOfString[] value();
    }

}
