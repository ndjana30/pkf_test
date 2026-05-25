package com.ndjana.pkf.applications;

import com.ndjana.pkf.Repositories.ResponsibleRepo;
import com.ndjana.pkf.interfaces.ResponsibleInterface;
import com.ndjana.pkf.models.Responsible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResponsibleApplication implements ResponsibleInterface {
    @Autowired
    ResponsibleRepo rr;

    @Override
    @Transactional
    public ResponseEntity<String> createResponsible(String email, String telephone, String name, String surname) {
        try{
            rr.save(new Responsible(email, telephone, name, surname));
            return new ResponseEntity<String>("Responsible created", HttpStatus.OK);
        }
        catch (Exception e)
        {
          return new ResponseEntity<String>(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
