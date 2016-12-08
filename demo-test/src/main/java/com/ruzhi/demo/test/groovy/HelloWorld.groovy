package com.ruzhi.demo.test.groovy

public class HelloWorld{
   
    public static void main(def args){
       def var="hello world"
		println var
		println var.class
    }  
	def getTime(date){return date.getTime();}
	def getDate(time){return new Date(time);}
}