package com.example.professorallocation;

//Espelha a resposta do servidor

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"allocations"})
public class DepartmentResponse {
    private int id;
    private String name;

    public DepartmentResponse() {
    }

    public DepartmentResponse(int id) {
        this.id = id;
    }

    public DepartmentResponse(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Retorna o nome do curso
    @Override
    public String toString() {
        return name;
    }
}
