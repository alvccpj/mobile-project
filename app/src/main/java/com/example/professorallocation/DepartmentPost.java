package com.example.professorallocation;

//Espelha o objeto que o backend espera receber
public class DepartmentPost {
    private String name;

    public DepartmentPost() {
    }

    public DepartmentPost(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
