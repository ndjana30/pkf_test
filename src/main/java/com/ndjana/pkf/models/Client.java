package com.ndjana.pkf.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "client")
@Entity
@Data
@NoArgsConstructor


public class Client {


    public Client(String email, Integer telephone, String name, String surname) {
        this.email = email;
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
    } //Constructor with some arguments for client object easy creation

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private Integer telephone;
    private String name;
    private String surname;

    @Nullable
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RDV> rdv = new ArrayList<>(); //One-to-many relationship

}
