package com.ndjana.pkf.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Table(name = "rdv")
@Entity
@Data
@NoArgsConstructor
public class RDV {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(unique = true,nullable = false)
    private Long id;

    private Date date;
    private String motif;
    private String duration="1 Hour";
    @Nullable
    private LocalTime time;

    @ManyToOne(fetch = FetchType.LAZY) //Many-to-one relationship with client class/client table in database
    @JoinColumn(name = "client_id") // Creates 'client_id' foreign key in rdv table
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY) //Many-to-one relationship with responsible class/responsible table in database
    @JoinColumn(name = "responsible_id") // Creates 'responsible_id' foreign key in rdv table
    private Responsible responsible;


    @ManyToOne(fetch = FetchType.LAZY) //Many-to-one relationship with S_Service class/service table in database
    @JoinColumn(name = "service_id") // Creates 'service_id' foreign key in rdv table
    private SService service;


}
