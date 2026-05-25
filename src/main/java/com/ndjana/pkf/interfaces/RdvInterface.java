package com.ndjana.pkf.interfaces;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
//repository, to use CRUD methods
public interface RdvInterface {
    public ResponseEntity<String> createRdv(LocalDate date, String motif, LocalTime time,
                                            Long client_id,
                                            Long responsible_id,
                                            Long Service_id);
}
