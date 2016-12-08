package com.ruzhi.demo.util;
/**
 * 
 * @author baike.lwb
 *
 */
public enum OpsErrorCode {
    
    SEARCH_OK(0, "OK"),
    PARSER_EXCEPTION(1, "PARSER_EXCEPTION"),
    PARSER_NULL(2, "PARSER_NULL"),
    SEARCH_FAIL(3, "SEARCH_FAIL"),
    SEARCH_OPS_EXCEPTION(4, "SEARCH_OPS_EXCEPTION");
    
    private int code;
    private String name;
    
    OpsErrorCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
