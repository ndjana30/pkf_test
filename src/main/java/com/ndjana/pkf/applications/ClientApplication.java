package com.ndjana.pkf.applications;

import com.ndjana.pkf.Repositories.ClientRepo;
import com.ndjana.pkf.interfaces.ClientInterface;
import com.ndjana.pkf.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientApplication implements ClientInterface {
    @Autowired
    ClientRepo cr;


    @Override
    @Transactional
    public ResponseEntity<String> createClient(String email, Integer telephone, String name, String surname) {
        Client client = new Client(email, telephone, name, surname);
        try {
            cr.save(client);
            return new ResponseEntity<String>("Client with name :"+client.getName()+" created", HttpStatus.OK);

        }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
