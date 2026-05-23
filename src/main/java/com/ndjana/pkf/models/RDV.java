package com.ndjana.pkf.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalTime;
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
    @Nullable
    private LocalTime time;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id") // Creates 'client_id' foreign key in rdv table
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id") // Creates 'responsible_id' foreign key in rdv table
    private Responsible responsible;

    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsible_id", referencedColumnName = "id")
    private Responsible responsible;
*/
    /*@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private S_Service service;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id") // Creates 'service_id' foreign key in rdv table
    private S_Service service;


}
