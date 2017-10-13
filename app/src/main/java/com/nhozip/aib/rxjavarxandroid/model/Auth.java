package com.nhozip.aib.rxjavarxandroid.model;

/**
 * Created by AIB on 10/13/2017.
 */

public class Auth {
    public String name;
    public int age;

    public Auth(String name,  int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
