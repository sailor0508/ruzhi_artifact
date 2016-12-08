package com.ruzhi.demo.test.self.validation;

import com.ruzhi.demo.test.HibernateValidation.MyValidatorUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by chunlong.wchl on 2015/6/2.
 */
public class Main {
    public static void main(String args[]) {
        test1();
    }

    public static void test1() {
        DemoForSelfValidation d = new DemoForSelfValidation();
        //d.setPerson(new Person());
        //d.setCompany("aa");
        System.out.println(MyValidatorUtil.validateModel(d));

        Person person = new Person();
        System.out.println(MyValidatorUtil.validateModel(person));
    }

    public static void test2() {
        /**
         * 这样在对类 Dog 的实例进行验证的时候，如果使用默认的组别（Default.class），则 name，ownername 和 type 都将进行验证；
         * 如果使用 Animal 的组别，则只会对 name 和 ownername 属性进行验证
         */
        Dog dog = new Dog();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Dog>> set = validator.validate(dog, Animal.class);
        for (ConstraintViolation<Dog> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }

    }

    public static void test3() {
        /**
         * 在类 User 中需要验证的字段上声明验证时所属的组别属性，如（groups=GroupA.class）,
         * 然后在 main 函数中调用 validator.validate(user,GroupA.class)) 方法
         验证器只会验证类 User 的 lastname 字段，如果使用 validator.validate(user))，
         则会使用 Default.class 组别，从而验证firstname 和 middlename 字段。

         如（groups=Group.class）,该验证将不再为属于 GroupA 和 GroupB 的约束进行验证，
         因为属于组序列(Group.class)中前面位置的 Default 组验证失败。
         只有当在 main 函数加入如下代码片段使属于 Default 组别的验证通过后，方可进行后续组别(GroupA，GroupB)的验证。
         * */
        User user = new User();
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user, Group.class);//
        for (ConstraintViolation<User> constraintViolation : set) {
            System.out.println(constraintViolation.getMessage());
        }
    }

}
