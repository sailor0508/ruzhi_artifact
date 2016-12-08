package com.ruzhi.demo.util.apt.junit;

import net.mindview.atunit.*;
import net.mindview.util.*;

public class AtUnitExternalTest extends AtUnitExample1 {
    public static final String a = "aaa";
    @Test
    boolean _methodOne() {
        return methodOne().equals("This is methodOne");
    }

    @Test
    boolean _methodTwo() {
        return methodTwo() == 2;
    }

    public static void main(String[] args){
        System.out.print("aa");
        String obslutelyPath = AtUnitExternalTest.class.getResource("").getPath();
        obslutelyPath = obslutelyPath.substring(1,obslutelyPath.length());
        //OSExecute.command("java " + obslutelyPath + " AtUnitExternalTest");
        OSExecute.command("cmd /k java "+obslutelyPath+"AtUnitExternalTest");
    }
} /* Output:
annotations.AtUnitExternalTest
  . _methodOne
  . _methodTwo This is methodTwo

OK (2 tests)
*///:~
