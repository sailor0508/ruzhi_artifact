
package com.ruzhi.demo.codemodel.dw.sample;



/**
 * This is the class to test the Singleton class.
 * 
 * @author Sonia (sxyu@cn.ibm.com)
 */
public class singletonTest {


    public static void main(String[] args) {
        while(true){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("aaa" + "bbb");
            Singleton.getInstance().sayHello("CodeModel");
        }
    }

}
