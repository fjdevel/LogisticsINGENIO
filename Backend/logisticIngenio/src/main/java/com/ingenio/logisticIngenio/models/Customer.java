package com.ingenio.logisticIngenio.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.common.aliasing.qual.Unique;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Size(max = 50)
    private String name;
    @Size(max = 250)
    private String address;
    @Size(max = 20)
    @Unique
    private String numDoc;

    @OneToOne
    @JoinColumn(name = "user_id")
    private AppUser user;
}
