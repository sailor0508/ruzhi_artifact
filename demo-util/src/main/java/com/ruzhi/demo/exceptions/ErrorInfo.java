package com.ruzhi.demo.exceptions;


/**
 * @author ruzhi
 */
public class ErrorInfo {
    public static String getErrorInfo(Exception e) {
        StringBuffer errorInfo = new StringBuffer();
        if(e == null){
            return errorInfo.toString();
        }
        if(e.getCause() != null){
            errorInfo.append(e.getCause().toString()+"\n");
            if(e.getCause().getCause() != null){
                errorInfo.append(e.getCause().getCause().toString()+"\n");
            }
        }
        if(e.getMessage() != null){
            errorInfo.append(e.getMessage().toString()+"\n");
        }
        if(e.getLocalizedMessage() != null){
            errorInfo.append(e.getLocalizedMessage().toString()+"\n");
        }
        
        return errorInfo.toString();
    }
}
