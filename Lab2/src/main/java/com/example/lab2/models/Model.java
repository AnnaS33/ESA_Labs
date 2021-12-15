package com.example.lab2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Model")
@Data

public class Model implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer ID;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "height")
    private Integer height;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "contractNumber")
    private Integer contractNumber;
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "managerId", nullable = false)
    @JsonIgnoreProperties({"models"})
    @ToString.Exclude
    private Manager manager;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"model"})
    @ToString.Exclude
    private List<Dress> dresses = new ArrayList<>();

}
