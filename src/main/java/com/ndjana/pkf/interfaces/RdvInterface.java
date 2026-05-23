package com.ndjana.pkf.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface RdvInterface {
    public ResponseEntity<String> createRdv(Date date,String motif,
                                            Long client_id,
                                            Long responsible_id,
                                            Long Service_id);
}
