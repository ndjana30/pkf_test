package com.ndjana.pkf.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    }

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private Integer telephone;
    private String name;
    private String surname;
    @Nullable
    @OneToOne(mappedBy = "client")
    private RDV rdv;
}
