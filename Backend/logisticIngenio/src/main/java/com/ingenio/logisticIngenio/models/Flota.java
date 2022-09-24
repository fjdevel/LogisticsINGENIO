package com.ingenio.logisticIngenio.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data // Create getters and setters
@NoArgsConstructor
@Table(name = "flota")
public class Flota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 8,max = 8)
    @Column(unique = true, nullable = false)
    private String numero;

}