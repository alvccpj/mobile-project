package com.example.professorallocation;

//espelha o objeto que o backend espera receber

public class CursoPost {
    private String name;

    public CursoPost() {
    }

    public CursoPost(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
