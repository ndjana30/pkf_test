package com.ndjana.pkf.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name="responsible")
@Entity
@Data
@NoArgsConstructor
public class Responsible {

    public Responsible(String email, String telephone, String name, String surname) //some args constructor
    {
        this.email = email;
        this.telephone = telephone;
        this.name = name;
        this.surname = surname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;
    private String telephone;
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "name must be between 3 and 20 characters")
    private String name;
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3, max = 20, message = "surname must be between 3 and 20 characters")
    private String surname;



    @Nullable
    @OneToMany(mappedBy = "responsible", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RDV> rdv = new ArrayList<>();

    @Nullable
    @OneToOne(mappedBy = "responsible")
    private SService service ;
}
