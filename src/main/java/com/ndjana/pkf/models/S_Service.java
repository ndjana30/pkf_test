package com.ndjana.pkf.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @OneToOne(mappedBy = "service")
    private RDV rdv;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responsible_id", referencedColumnName = "id")
    private Responsible responsible;
}
