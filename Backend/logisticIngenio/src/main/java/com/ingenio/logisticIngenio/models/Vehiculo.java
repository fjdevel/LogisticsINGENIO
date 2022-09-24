package com.ingenio.logisticIngenio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data // Create getters and setters
@NoArgsConstructor
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 6, max = 6)
    @Column(unique = true, nullable = false)
    private String placa;
}