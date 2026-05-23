package com.ndjana.pkf.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "s_service")
@Data
@NoArgsConstructor
public class S_Service {
    @Id
    @GeneratedValue
    private Long id;
    private String name;


    @Nullable
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true) //One-to-many relationship with Rendezvous class/table
    private List<RDV> rdv = new ArrayList<>();

    @Nullable
    @OneToOne(cascade = CascadeType.ALL) //One-to-one relationship with responsible
    @JoinColumn(name = "responsible_id", referencedColumnName = "id")
    private Responsible responsible;
}
