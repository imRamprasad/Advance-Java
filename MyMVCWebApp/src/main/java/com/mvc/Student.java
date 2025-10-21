package com.mvc;

public class Student {
    private String name;

    public Student() {}

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return "Welcome, " + name + "! You are successfully following MVC pattern.";
    }
}
