package com.example.moeda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "professores")
public class Professor extends Pessoa {
    @ManyToOne
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;

    // Getters e Setters
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
} 