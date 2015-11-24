package com.MemAndMybatis.Vo;

import java.io.Serializable;

/**
 * Created by xiaobai on 15-11-10.
 */
public class TestBean implements Serializable{

    private static final long serialVersionUID = -5326794791222239369L;
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
