package com.example.lab2.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity //класс должен быть в таблице
@Table(name = "Dress")
@Data

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
    //private List<Dress> dresses;

    @ManyToOne()
    @JoinColumn(name = "model_id")
    @JsonIgnoreProperties({"dresses", "manager"})
    @ToString.Exclude
    private Model model;


}

