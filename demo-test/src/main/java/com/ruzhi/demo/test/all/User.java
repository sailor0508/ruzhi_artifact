package com.ruzhi.demo.test.all;

@DBTable(name="user")
public class User {

    @SQLInteger(name="id",constraints = @Constraints(primarykey=true))
    public Integer id;

    @SQLString(value=30)
    public String name;

    @SQLString(name="passwd",constraints=@Constraints(allownull=false))
    public String password;

    /*可以不用
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }*/
}
