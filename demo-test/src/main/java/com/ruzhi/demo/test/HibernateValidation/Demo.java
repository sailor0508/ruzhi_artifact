package com.ruzhi.demo.test.HibernateValidation;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public class Demo {
    @NotNull
    @Size(max = 30)
    private String addressline1;

    @Size(min = 5, max = 5)
    private String addressline2;

    @NotNull(message = "{user.id.null}")
    private Long id;

    @NotEmpty(message = "{user.name.null}")
    @Length(min = 5, max = 20, message = "{user.name.length.illegal}")
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "{user.name.illegal}")
    private String name;

}
