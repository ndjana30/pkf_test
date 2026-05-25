package com.ndjana.pkf.applications;

import com.ndjana.pkf.Repositories.ResponsibleRepo;
import com.ndjana.pkf.Repositories.ServiceRepository;
import com.ndjana.pkf.interfaces.ServiceInterface;
import com.ndjana.pkf.models.Responsible;
import com.ndjana.pkf.models.S_Service;
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
            S_Service ss = new S_Service();
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
            Optional<S_Service> s = sr.findById(service_id);
            Optional<Responsible> r = rr.findById(responsible_id);

            s.flatMap(ss -> r).ifPresent(rs -> {
                System.out.println("service found");
                System.out.println("responsible found");
                s.get().setResponsible(r.get());
                sr.save(s.get());
            });
            /*if (s.isPresent())
            {
                System.out.println("service found");
                if (r.isPresent())
                {
                    System.out.println("responsible found");
                    s.get().setResponsible(r.get());
                    sr.save(s.get());
                }
                else{
                    return new ResponseEntity<>("responsible not found",HttpStatus.BAD_REQUEST);
                }

            }
            else{
                return new ResponseEntity<>("Service not found",HttpStatus.BAD_REQUEST);
            }*/
            return new ResponseEntity<String>("Responsible Assigned",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>(e.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
        }


    }
}
