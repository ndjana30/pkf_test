package com.ndjana.pkf.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Table(name = "rdv")
@Entity
@Data
@NoArgsConstructor
public class RDV {
    @Id
    @GeneratedValue
    private Long id;
    private Date date;
    private String motif;
    private String duration="1 Hour";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsible_id", referencedColumnName = "id")
    private Responsible responsible;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private S_Service service;


}
