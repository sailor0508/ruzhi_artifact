package com.ruzhi.demo.test.self.validation;

/**
 * Created by chunlong.wchl on 2015/6/3.
 */
public class Dog implements Animal {

    private String name;
    private String ownerName;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setType(String type) {
        this.type = type;
    }

    @SelfNotEmpty(message = "type of the dog may be empty")
    public String getType() {
        return type;
    }
}

