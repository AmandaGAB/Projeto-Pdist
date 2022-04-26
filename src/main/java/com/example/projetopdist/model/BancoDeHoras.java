package com.example.projetopdist.model;


import javax.persistence.*;
import java.io.Serializable;


@Entity(name = "horas")
public class BancoDeHoras implements Serializable {

    private static final long serialVerionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String data;
    private Integer hora;


    public BancoDeHoras(){}

    public BancoDeHoras(String data, Integer hora){
        this.data = data;
        this.hora = hora;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Number getHora() {
        return hora;
    }

    public void setHora(Integer hora) {
        this.hora = hora;
    }

}
