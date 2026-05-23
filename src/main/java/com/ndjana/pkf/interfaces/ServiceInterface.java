package com.ndjana.pkf.interfaces;

import org.springframework.http.ResponseEntity;

public interface ServiceInterface {
    public ResponseEntity<String> createService(String name);
    public ResponseEntity<String> AssignResponsible(Long service_id,Long responsible_id);
}
