package com.example.projetopdist.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nome;
    private String telefone;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="funcionario_id")
    private List<BancoDeHoras> horas;

    public Funcionario(){}

    public Funcionario(String nome, String telefone, String email){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }
    // Construtor com horas
    public Funcionario(String nome, String telefone, String email, List<BancoDeHoras> horas){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.horas = horas;
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BancoDeHoras> getHoras() {
        return horas;
    }

    public void setHoras(List<BancoDeHoras> horas) {
        this.horas = horas;
    }
}
