package com.example.moeda.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa extends Usuario {
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(nullable = false)
    private int saldo;
    
    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "pessoas"})
    private Instituicao instituicao;

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }
} 