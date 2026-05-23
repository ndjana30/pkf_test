package com.ndjana.pkf.applications;

import com.ndjana.pkf.Repositories.ClientRepo;
import com.ndjana.pkf.Repositories.RdvRepo;
import com.ndjana.pkf.Repositories.ResponsibleRepo;
import com.ndjana.pkf.Repositories.ServiceRepository;
import com.ndjana.pkf.interfaces.RdvInterface;
import com.ndjana.pkf.models.Client;
import com.ndjana.pkf.models.RDV;
import com.ndjana.pkf.models.Responsible;
import com.ndjana.pkf.models.S_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

@Service
public class RdvApplication implements RdvInterface {

    @Autowired
    ClientRepo clientRepo;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    ResponsibleRepo responsibleRepo;
    @Autowired
    RdvRepo rdvRepo;

    @Override
    public ResponseEntity<String> createRdv(Date date, String motif, LocalTime time, Long client_id, Long responsible_id, Long service_id) {
        RDV rdv  =new RDV();
        Optional<Client> client = clientRepo.findById(client_id);
        Optional<S_Service> service  =serviceRepository.findById(service_id);
        Optional<Responsible> responsible = responsibleRepo.findById(responsible_id);
        try {
            client.flatMap(c -> service).flatMap(s -> responsible).ifPresent(
                    ts -> {
                        System.out.println("client found");
                        System.out.println("service found");
                        System.out.println("responsible found");
                        rdv.setClient(client.get());
                        rdv.setResponsible(responsible.get());
                        rdv.setService(service.get());
                        rdv.setDate(date);
                        rdv.setMotif(motif);
                        rdv.setTime(time);
                        rdvRepo.save(rdv);
                    }
            );
            return new ResponseEntity<>("Rendez-vous cerated",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
