package com.ndjana.pkf.interfaces;

import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.Date;

public interface RdvInterface {
    public ResponseEntity<String> createRdv(Date date,String motif, LocalTime time,
                                            Long client_id,
                                            Long responsible_id,
                                            Long Service_id);
}
