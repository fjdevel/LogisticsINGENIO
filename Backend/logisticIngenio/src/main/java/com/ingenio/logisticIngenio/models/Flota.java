package com.ingenio.logisticIngenio.models;

import javax.persistence.*;

@Entity
@Table(name = "flota")
public class Flota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}