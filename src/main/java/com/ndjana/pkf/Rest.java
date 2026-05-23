package com.ndjana.pkf;

import com.ndjana.pkf.applications.ClientApplication;
import com.ndjana.pkf.applications.RdvApplication;
import com.ndjana.pkf.applications.ResponsibleApplication;
import com.ndjana.pkf.applications.ServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;

@RestController
@RequestMapping("pkf/rdv")
public class Rest {

    @Autowired
    public ClientApplication ca;
    @Autowired
    public ResponsibleApplication ra;
    @Autowired
    ServiceApplication sa;
    @Autowired
    RdvApplication rdvApplication;

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

    @PostMapping("create/{service_id}/{responsible_id}/{client_id}")
    public ResponseEntity<String> createRdv(@PathVariable Long service_id,
                                            @PathVariable Long responsible_id,
                                            @PathVariable Long client_id,
                                            @RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date date,
                                            @RequestParam("motif") String motif,
                                            @RequestParam("time") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime time) {

        return rdvApplication.createRdv(date, motif,time, client_id, responsible_id, service_id);
    }

}
