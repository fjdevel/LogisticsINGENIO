package com.ingenio.logisticIngenio.models;

import javax.persistence.*;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}