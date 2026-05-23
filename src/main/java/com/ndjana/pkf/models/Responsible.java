package com.ndjana.pkf.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name="responsible")
@Entity
@Data
@NoArgsConstructor
public class Responsible {

    public Responsible(String email, Integer telephone, String name, String surname) //some args constructor
    {
        this.email = email;
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private Integer telephone;
    private String name;
    private String surname;



    @Nullable
    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RDV> rdv = new ArrayList<>();

    @Nullable
    @OneToOne(mappedBy = "responsible")
    private S_Service service ;
}
