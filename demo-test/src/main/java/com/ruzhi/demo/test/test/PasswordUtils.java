package com.ruzhi.demo.test.test;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个使用如上注解密码工具类
 *
 * @author <a href="mailto:cn.java.river@gmail.com">Tao</a>
 * @since 1.0
 */
public class PasswordUtils {
    @MyUseCase(id = 47, description = "密码必须包含至少一个数字")
    public boolean validatePassword(String password) {
        return (password.matches("\\w*\\d\\w*"));
    }

    @MyUseCase(id = 48)
    public String encryptPassword(String password) {
        return new StringBuilder(password).reverse().toString();
    }

    @MyUseCase(id = 49, description = "新密码不能和曾经用过的密码重复")
    public boolean checkForNewPassword(List<String> prevPasswords, String password) {
        return !prevPasswords.contains(password);
    }

    /**
     * @deprecated 楼主决定不推荐使用此方法了，自己随便找个个其他的试试吧.
     */
    @Deprecated
    public void deprecatedMethod() {
    }

    @SuppressWarnings({"unused", "rawtypes"})
    public void unsafeMethod() {
        //@SuppressWarnings ({ "unused", "rawtypes" }) //用在这里仍然可以，因为此注解支持修饰类和本地变量等类型。
        List unsafeList = new ArrayList<String>();
    }
}
