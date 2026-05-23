package com.ndjana.pkf.interfaces;

import org.springframework.http.ResponseEntity;

//repository, to use CRUD methods
public interface ResponsibleInterface {
    public ResponseEntity<String> createResponsible(String email,Integer telephone, String name, String surname);
}
