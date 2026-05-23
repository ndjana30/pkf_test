package com.ndjana.pkf;

import com.ndjana.pkf.applications.ClientApplication;
import com.ndjana.pkf.applications.ResponsibleApplication;
import com.ndjana.pkf.applications.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pkf/rdv")
public class Rest {

    @Autowired
    public ClientApplication ca;
    @Autowired
    public ResponsibleApplication ra;
    @Autowired
    ServiceApplication sa;

    @PostMapping("client/create")
    public ResponseEntity<String> createClient(@RequestParam("email") String email,
                                               @RequestParam("telephone") Integer telephone,
                                               @RequestParam("name") String name,
                                               @RequestParam("surname") String surname) {

        return ca.createClient(email, telephone, name, surname);
    }

    @PostMapping("responsible/create")
    public ResponseEntity<String> createResponsible(@RequestParam("email") String email,
                                               @RequestParam("telephone") Integer telephone,
                                               @RequestParam("name") String name,
                                               @RequestParam("surname") String surname) {

        return ra.createResponsible(email, telephone, name, surname);
    }

    @PostMapping("service/create")
    public ResponseEntity<String> createService(@RequestParam("name") String name) {

        return sa.createService(name);
    }

    @PostMapping("service/{service_id}/{responsible_id}")
    public ResponseEntity<String> assignResponsibleToService(@PathVariable Long service_id,
                                                             @PathVariable Long responsible_id) {

        return sa.AssignResponsible(service_id,responsible_id);
    }

}
