package com.ndjana.pkf.applications;

import com.ndjana.pkf.repositories.ResponsibleRepo;
import com.ndjana.pkf.repositories.ServiceRepository;
import com.ndjana.pkf.interfaces.ServiceInterface;
import com.ndjana.pkf.models.Responsible;
import com.ndjana.pkf.models.SService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ServiceApplication implements ServiceInterface{

    @Autowired
    ServiceRepository sr;
    @Autowired
    ResponsibleRepo rr;

    @Override
    @Transactional
    public ResponseEntity<String> createService(String name) {
        try{
            SService ss = new SService();
            ss.setName(name);
            sr.save(ss);
            return new ResponseEntity<>("Service created", HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<String> AssignResponsible(Long service_id,Long responsible_id) {
        try{
            Optional<SService> s = sr.findById(service_id);
            Optional<Responsible> r = rr.findById(responsible_id);

            s.flatMap(ss -> r).ifPresent(rs -> {
                System.out.println("service found");
                System.out.println("responsible found");
                s.get().setResponsible(r.get());
                sr.save(s.get());
            });

            return new ResponseEntity<String>("Responsible Assigned",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }


    }
}
