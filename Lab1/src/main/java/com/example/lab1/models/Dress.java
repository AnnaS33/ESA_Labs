package com.example.lab1.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Dress")
@Data

//Создаём сущность платье
public class Dress implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "color")
    private String color;

    @Column(name = "numberD")
    private Integer numberD;

    @ManyToOne
    @JoinColumn(name = "model_id")
    //Игнорируем эти поля
    @JsonIgnoreProperties({"dresses","manager"})
    private Model model;


}
