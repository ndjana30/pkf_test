package com.ndjana.pkf.interfaces;

import org.springframework.http.ResponseEntity;

public interface ClientInterface {
    public ResponseEntity<String> createClient(String email,Integer telephone,String name,String surname);

}
